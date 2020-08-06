package com.zb.sys.service;

import com.zb.App;
import com.zb.base.utils.StringUtil;
import com.zb.sys.entity.Area;
import com.zb.sys.entity.AreaCopy;
import com.zb.sys.service.impl.AreaCopyServiceImpl;
import com.zb.sys.service.impl.AreaServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by bzheng on 2019/12/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AreaServiceImplTest {

    @Autowired
    AreaServiceImpl areaService;

    @Autowired
    AreaCopyServiceImpl areaCopyService;

    List<Area> list;

    List<AreaCopy> listCopy;

    @Before
    public void init() {
        list = areaService.list();
    }

    @Test
    public void test() {
        // 处理数据
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        // 存放省市
        Map<String, String> provinceMap = new HashMap<>();
        // 存放城市
        Map<String, String> cityMap = new HashMap<>();
        // 省的后缀
        String provinceSuffix = "0000";
        // 城市的后缀
        String citySuffix = "00";
        listCopy = list.stream().map(area -> {
            AreaCopy copy = new AreaCopy();
            BeanUtils.copyProperties(area, copy);
            return copy;
        }).collect(Collectors.toList());

        listCopy.forEach(area -> {
            String areaCode = area.getAreaCode();
            // 如果以0000结尾就是省
            if (StringUtil.endsWith(areaCode, provinceSuffix)) {
                // 加入省
                provinceMap.put(areaCode, area.getAreaName());
                // 设置父code
                area.setParentCode("0");
            } else if (StringUtil.endsWith(areaCode, citySuffix)) {
                // 获取省的编码 和 省名称
                String provinceCode = areaCode.substring(0, 2) + provinceSuffix;
                String province = Optional.ofNullable(provinceMap.get(provinceCode)).orElse("");
                // 加入城市map
                cityMap.put(areaCode, area.getAreaName());
                // 将城市的名称截取
                area.setAreaName(area.getAreaName().replaceAll(province, ""));
                // 设置父code
                area.setParentCode(provinceCode);
            } else {
                // 县级城市
                // 获取市的编码 和 市名称
                String cityCode = areaCode.substring(0, 4) + citySuffix;
                String city = Optional.ofNullable(cityMap.get(cityCode)).orElse("");
                // 将城市的名称截取
                area.setAreaName(area.getAreaName().replaceAll(city, ""));
                // 设置父code
                area.setParentCode(cityCode);
            }
        });
    }

    @After
    public void after() {
        if (CollectionUtils.isEmpty(listCopy)) {
            return;
        }
        // 新增到copy数据库
        areaCopyService.saveBatch(listCopy);
    }

}
