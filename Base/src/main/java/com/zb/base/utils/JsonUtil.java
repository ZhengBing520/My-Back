package com.zb.base.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.zb.base.define.CommonConst;
import com.zb.base.json.SimpleFilterBean;
import com.zb.base.json.filter.EmptyJacksonFilter;
import com.zb.base.json.filter.SimpleJacksonFilter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/5/12.
 */
@SuppressWarnings("unchecked")
public class JsonUtil {
    //此空过滤器必须定义在全局 GLOBAL_OBJECT_MAPPER 之前
    private final static FilterProvider EMPTY = new SimpleFilterProvider().setDefaultFilter(new EmptyJacksonFilter());
    public final static ObjectMapper GLOBAL_OBJECT_MAPPER = withConfig(new ObjectMapper());


    public static JavaType getJavaType(Type type) {
        //1、判断是否带有泛型
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            //1.1、获取泛型类型
            Class rawClass = (Class) ((ParameterizedType) type).getRawType();
            JavaType[] javaTypes = new JavaType[actualTypeArguments.length];
            for (int i = 0; i < actualTypeArguments.length; i++) {
                //1.2、泛型也可能带有泛型，递归获取
                javaTypes[i] = getJavaType(actualTypeArguments[i]);
            }
            return TypeFactory.defaultInstance().constructParametricType(rawClass, javaTypes);
        } else {
            //2、简单类型直接用该类构建JavaType
            Class cla = (Class) type;
            return TypeFactory.defaultInstance().constructParametricType(cla, new JavaType[0]);
        }
    }


    /**
     * 1、为ObjectMapper重新设置MapSerializer,使其能使用PropertyFilter过滤属性,并为所有的Map添加过滤器
     * 如果设置了map过滤,则必须为objectMapper设置默认过滤器(默认设置空的过滤器)
     * 2、设置所有Number属性的 输出为字符串(Long类型数字传入前端会进行四舍五入导致精度丢失,为了避免这种情况,所有的数字全部采用String格式化)
     * 3、设置忽略null属性输出
     * 4、设置在解析json字符串为实体类时候,忽略多余的属性
     * 不会生成新的ObjectMapper,只会改变当前传入的ObjectMapper
     *
     * @param t
     * @return
     */
    public static <T extends ObjectMapper> T withConfig(T t) {
        try {
            //1、设置map过滤器
            SerializerProvider provider = new DefaultSerializerProvider.Impl().createInstance(t.getSerializationConfig(), t.getSerializerFactory());
            JsonSerializer mapSerializer = provider.findValueSerializer(Map.class);
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(Map.class, mapSerializer.withFilterId("bcd"));
            //2、设置所有Number属性的 输出为字符串
            simpleModule.addSerializer(Number.class, ToStringSerializer.instance);
            t.registerModule(simpleModule);
            t.setFilterProvider(EMPTY);
            //3、设置忽略null属性输出
            t.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            //4、设置在解析json字符串为实体类时候,忽略多余的属性
            t.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //5、设置在序列化时候遇到空属性对象时候,不抛出异常
            t.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return t;
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 此方法会调用 withConfig 改变objectMapper
     *
     * @param object
     * @param filters
     * @return
     */
    public static String toJson(Object object, SimpleFilterBean... filters) {
        return toJson(GLOBAL_OBJECT_MAPPER, object, filters);
    }


    /**
     * 此方法会调用 withConfig 改变objectMapper
     *
     * @param object
     * @param filters
     * @return
     */
    public static String toJson(ObjectMapper objectMapper, Object object, SimpleFilterBean... filters) {
        if (objectMapper != GLOBAL_OBJECT_MAPPER) {
            withConfig(objectMapper);
        }
        objectMapper.setFilterProvider(new SimpleFilterProvider().setDefaultFilter(new SimpleJacksonFilter(filters)));
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 合并 SimpleFilterBean[]
     * 相同的过滤器,对其中的子集进行并集
     *
     * @param filterss
     * @return
     */
    public static SimpleFilterBean[] combineFilters(SimpleFilterBean[]... filterss) {
        return combineFilters(Arrays.stream(filterss).flatMap(e -> e == null ? Stream.empty() : Arrays.stream(e)).toArray(SimpleFilterBean[]::new));
    }

    /**
     * 合并 SimpleFilterBean
     * 相同的过滤器,对其中的子集进行并集
     *
     * @param filters
     * @return
     */
    public static SimpleFilterBean[] combineFilters(SimpleFilterBean... filters) {
        if (filters == null || filters.length == 0) {
            return new SimpleFilterBean[0];
        }
        return Arrays.stream(filters).filter(Objects::nonNull).reduce(new HashMap<String, SimpleFilterBean>(), (res, filter) -> {
            String key = filter.getClazz().getName();
            SimpleFilterBean val = res.get(key);
            if (val == null) {
                res.put(key, filter);
            } else {
                val.getExcludes().addAll(filter.getExcludes());
            }
            return res;
        }, (res1, res2) -> {
            res1.putAll(res2);
            return res1;
        }).values().stream().toArray(SimpleFilterBean[]::new);
    }

    /**
     * 解析过滤字符串数组成json过滤器
     * 依次循环过滤字符串数组，找当前 clazz 参数对应的字段，最后形成找到结果的过滤器
     * example:
     * parseJsonFiltersByParam( EnumTypeDTO.class , new String[]{"enumItemDTOSet.enumTypeDTO"} )
     * 生成
     * SimpleFilterBean filter1=new SimpleFilterBean(EnumItemDTO.class);
     * filter1.getExcludes().add("enumTypeDTO");
     * return new SimpleFilterBean[]{filter1};
     *
     * @param clazz
     * @param filterStrs
     * @return
     */
    public static SimpleFilterBean[] parseJsonFiltersByParam(Class clazz, String... filterStrs) {
        //1、非空验证
        if (filterStrs == null || filterStrs.length == 0) {
            return new SimpleFilterBean[0];
        }
        //2、构造过滤器集合，供返回
        Map<String, SimpleFilterBean> filterMap = new HashMap<>();
        //3、循环过滤器字符串数组，解析每一个字符串，形成对应的过滤器
        A:
        for (int i = 0; i <= filterStrs.length - 1; i++) {
            //4、过滤字符串非空验证
            if (filterStrs[i] == null || filterStrs[i].trim().equals("")) {
                continue;
            }
            //5、定义字段数组，接收split字符串的结果
            String[] fieldStrArr;
            if (filterStrs[i].indexOf('.') == -1) {
                fieldStrArr = new String[]{filterStrs[i]};
            } else {
                fieldStrArr = filterStrs[i].split("\\.");
            }

            //6、定义当前class和当前field
            Class curClazz = clazz;
            Field curField;
            //7、循环字段数组
            for (int j = 0; j <= fieldStrArr.length - 1; j++) {
                //8、结合当前类、字段数组，得到当前的字段；取不倒字段则证明过滤器字符串错误，跳过外层循环
                //8.1、如果是Map,则直接构造过滤器返回
                if (Map.class.isAssignableFrom(clazz)) {
                    SimpleFilterBean filter = filterMap.get(Map.class.getName());
                    if (filter == null) {
                        filter = new SimpleFilterBean(Map.class);
                    }
                    filter.getExcludes().add(fieldStrArr[j]);
                    filterMap.put(Map.class.getName(), filter);
                }
                //8.2、否则则进行字段的验证
                try {
                    curField = curClazz.getField(fieldStrArr[j]);
                } catch (NoSuchFieldException e) {
                    try {
                        curField = curClazz.getDeclaredField(fieldStrArr[j]);
                    } catch (NoSuchFieldException e1) {
                        continue A;
                    }
                }
                //9、如果是循环结束，则形成过滤器并加入集合中
                if (j == fieldStrArr.length - 1) {
                    String key = curClazz.getName();
                    //9.1、去除重复filter
                    SimpleFilterBean filter = filterMap.get(key);
                    if (filterMap.get(key) == null) {
                        filter = new SimpleFilterBean(curClazz);
                        filterMap.put(key, filter);
                    }
                    filter.getExcludes().add(curField.getName());
                } else {
                    //10、取到当前字段，如果是集合类，则需要取到集合中泛型类型，同时将当前类设置成泛型类型
                    if (Collection.class.isAssignableFrom(curField.getType())) {
                        curClazz = (Class) ((ParameterizedType) curField.getGenericType()).getActualTypeArguments()[0];
                    } else {
                        //11、如果是非集合类，则取字段类型，如果字段类型是基础类型，则跳过外层循环,否则将当前类设置成字段类型
                        curClazz = curField.getType();
                        for (int n = 0; n <= CommonConst.BASE_DATA_TYPE.length - 1; n++) {
                            if (CommonConst.BASE_DATA_TYPE[n].isAssignableFrom(curClazz)) {
                                continue A;
                            }
                        }
                    }
                }
            }
        }

        //12、返回结果集
        if (filterMap.size() == 0) {
            return new SimpleFilterBean[0];
        }
        return filterMap.values().stream().toArray(SimpleFilterBean[]::new);
    }

    /**
     * 支持多个过滤器类型
     *
     * @param paramArr [
     *                 [UserBean.class,["id","name",......]],
     *                 [RoleBean.class,["id","name",......]],
     *                 ]
     * @return
     */
    public static SimpleFilterBean[] parseJsonFiltersByParam(Object[]... paramArr) {
        if (paramArr == null || paramArr.length == 0) {
            return new SimpleFilterBean[0];
        }
        List<SimpleFilterBean> SimpleFilterBeanList = new ArrayList<>();
        //1、循环调用生成filter数组
        for (int i = 0; i <= paramArr.length - 1; i++) {
            Class clazz = (Class) paramArr[i][0];
            String[] filterStrArr = (String[]) paramArr[i][1];
            SimpleFilterBean[] curSimpleFilterBean = parseJsonFiltersByParam(clazz, filterStrArr);
            if (curSimpleFilterBean == null || curSimpleFilterBean.length == 0) {
                continue;
            }
            SimpleFilterBeanList.addAll(Arrays.asList(curSimpleFilterBean));
        }
        //2、合并多次调用的返回结果,将相同类的filter整合在一起
        if (SimpleFilterBeanList.isEmpty()) {
            return new SimpleFilterBean[0];
        }
        Map<String, SimpleFilterBean> filterMap = SimpleFilterBeanList.stream().collect(Collectors.toMap(
                (filter) -> filter.getClazz().getName(),
                (filter) -> filter,
                (filter1, filter2) -> {
                    filter1.getExcludes().addAll(filter2.getExcludes());
                    return filter1;
                }
        ));
        //3、返回结果集
        if (filterMap.size() == 0) {
            return new SimpleFilterBean[0];
        }
        return filterMap.values().stream().toArray(SimpleFilterBean[]::new);
    }


    public static void main(String[] args) throws JsonProcessingException {
        int i=1;
        System.out.println((++i)+(++i));
        TestBean t1=new TestBean();
        t1.setId(1);
        t1.setName("t1");
        TestBean t2=new TestBean();
        t2.setId(2);
        t2.setName("t2");
        TestBean t3=new TestBean();
        t2.setId(3);
        t2.setName("t3");
        t1.setDataList(Arrays.asList(t2,t3));
        ObjectMapper objectMapper=JsonUtil.withConfig(new ObjectMapper());
        SimpleModule simpleModule=new SimpleModule();
        simpleModule.addDeserializer(TestBean.class, new JsonDeserializer<TestBean>() {
            public TestBean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                if (p.isExpectedStartObjectToken()) {
                    p.nextToken();
                }
                if(p.hasTokenId(JsonTokenId.ID_FIELD_NAME)){
                }

                p.currentToken();
                return null;
            }
        });
        objectMapper.registerModule(simpleModule);
        TestBean res= objectMapper.readValue(JsonUtil.toJson(t1),TestBean.class);
    }

}

class TestBean{
    private Integer id;
    private String name;
    private List<TestBean> dataList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<TestBean> dataList) {
        this.dataList = dataList;
    }
}
