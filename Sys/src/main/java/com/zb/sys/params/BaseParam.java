package com.zb.sys.params;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zb.sys.download.SqlParam;

import java.util.Objects;

/**
 * Created by bzheng on 2019/12/10.
 * 参数条件
 */
public class BaseParam<T> implements SqlParam {

    private Wrapper<T> queryWrapper;

    public BaseParam(Wrapper<T> queryWrapper) {
        this.queryWrapper = queryWrapper;
    }

    public Wrapper<T> getQueryWrapper() {
        return queryWrapper;
    }

    @Override
    public String toString() {
        if (Objects.isNull(queryWrapper)) {
            return super.toString();
        }
        return queryWrapper.getSqlSelect();
    }
}
