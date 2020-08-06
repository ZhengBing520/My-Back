package com.zb.generator.code;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.zb.generator.bean.BaseBean;
import com.zb.generator.code.config.CodeConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by bzheng on 2019/11/26.
 */
public class CodeGenerator {

    private final static String SPRING_PROPERTIES_PATH = CodeConfig.projectPath + "/src/main/resources/application.yml";

    private static String url;
    private static String username;
    private static String password;
    private static String driverClassName;



    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        initDataBase();
        String moduleName = scanner("模块名");
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 输出路径
        gc.setOutputDir(CodeConfig.buildTargetPath(moduleName, ""));
//        gc.setOutputDir("D:\\workspace-sts\\0520adv\\02_mp_springboot/src/main/java");
        gc.setAuthor("bzheng");
        gc.setOpen(false);//当代码生成完成之后是否打开代码所在的文件夹
         gc.setSwagger2(true); // 实体属性 Swagger2 注解
//        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);
        gc.setBaseColumnList(true);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverClassName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();

//        pc.setModuleName(moduleName);
        pc.setParent(CodeConfig.getPackagePrefix() + moduleName.toLowerCase());//controller entity  service  service.impl
       /* pc.setController("controller");
        pc.setEntity("bean");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapper.xml");*/
        pc.setXml(null);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //设置字段和表名的是否把下划线完成驼峰命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //设置生成的实体类继承的父类
        strategy.setSuperEntityClass("com.zb.rdb.bean.BaseBean");
        strategy.setEntityLombokModel(true);//是否启动lombok
        strategy.setRestControllerStyle(true);//是否生成resetController
        // 公共父类
//        strategy.setSuperControllerClass("com.sxt.BaseController");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns(new String[]{"id","create_time","create_user_id","create_user_name",
       //         "update_time","update_user_id","update_user_name","create_ip","update_ip"});
        //要设置生成哪些表 如果不设置就是生成所有的表
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // 排除的表名
//        strategy.setExclude("flyway_schema_history");
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setTablePrefix("t_sys_");
        mpg.setStrategy(strategy);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return CodeConfig.projectPath + File.separator + moduleName + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        TemplateConfig templateConfig = new TemplateConfig();
        //自定义模版
//        templateConfig.setController("/templates/btl/controller.java");
//        templateConfig.setEntity("/templates/btl/entity.java");
        //关闭默认的mapper xml生成
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        //使用beetl模版引擎
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

    private static void initDataBase() {
        try {
            Map<String, Object> props = new HashMap<>();
            Yaml yaml = new Yaml();
            //1、加载spring配置
            LinkedHashMap dataMap = (LinkedHashMap) yaml.load(new FileInputStream(Paths.get(SPRING_PROPERTIES_PATH).toFile()));
            LinkedHashMap springMap = (LinkedHashMap) dataMap.get("spring");
            LinkedHashMap dataSourceMap = (LinkedHashMap) springMap.get("datasource");
            //1.1、取出配置文件后缀
            String suffix = springMap.get("profiles.active").toString();
            if (!StringUtils.isEmpty(suffix)) {
                //1.2、如果有激活的配置文件,则加载
                String activePathStr = SPRING_PROPERTIES_PATH.substring(0, SPRING_PROPERTIES_PATH.lastIndexOf(".")) + "-" + suffix + "." + SPRING_PROPERTIES_PATH.substring(SPRING_PROPERTIES_PATH.indexOf(".") + 1);
                Path activePath = Paths.get(activePathStr);
                if (Files.exists(activePath)) {
                    dataMap = (LinkedHashMap) yaml.load(new FileInputStream(activePath.toFile()));
                    springMap = (LinkedHashMap) dataMap.get("spring");
                    dataSourceMap = (LinkedHashMap) springMap.get("datasource");
                }
            }
            //2、取出值
             url = dataSourceMap.get("url").toString();
             username = dataSourceMap.get("username").toString();
             password = dataSourceMap.get("password").toString();
             driverClassName = dataSourceMap.get("driverClassName").toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
