package com.zb.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zb.sys.download.DownloadService;

/**
 * Created by bzheng on 2019/12/10.
 * 所有service先继承BaseService
 */
public interface BaseService<T> extends DownloadService<T>,IService<T> {

    // 导出
    void export(Wrapper<T> wrapper);
}
