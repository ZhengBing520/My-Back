package com.zb.sys.download;

/**
 * Created by bzheng on 2019/4/29.
 * 参数条件 -- 自己实现,例如：Wrapper（mybaties-plus）、condition（jpa）、sql（jdbctemplate）、map（...）
 * 子类实现，查询时强转
 */
public interface SqlParam {

    // 打印参数条件
    String toString();

}
