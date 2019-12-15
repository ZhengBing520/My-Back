package com.zb.sys.download;

import java.util.List;

/**
 * Created by bzheng on 2019/12/5.
 * 导出用到的service
 */
public interface DownloadService<T> {

    /**
     *  获取总数
     * @param sqlParam 参数条件
     * @return
     */
    long count(SqlParam sqlParam);

    /**
     * 分页查询
     * @param sqlParam 参数条件
     * @param downloadPage 分页参数
     * @return
     */
    List<T> page(SqlParam sqlParam, DownloadPage downloadPage);
}
