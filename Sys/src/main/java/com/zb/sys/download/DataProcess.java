package com.zb.sys.download;

import com.zb.rdb.bean.SuperBaseBean;

import java.util.List;

/**
 * Created by bzheng on 2019/12/4.
 * 数据处理
 */
@FunctionalInterface
public interface DataProcess<T> {

    // 处理数据
    boolean apply(List<T> data, String filePath);
}
