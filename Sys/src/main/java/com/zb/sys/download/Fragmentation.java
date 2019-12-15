package com.zb.sys.download;

import com.zb.sys.download.anno.ExcludeProperty;
import com.zb.sys.download.utils.CSVUtil;
import com.zb.sys.download.utils.PathUtil;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Created by bzheng on 2019/12/4.
 * 任务分片 -- 一个任务可以分成多份
 */
public class Fragmentation<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Fragmentation.class);

    @ApiModelProperty(value = "数据源")
    private BiFunction<SqlParam, DownloadPage, List<T>> function;

    @ApiModelProperty(value = "分页参数")
    private DownloadPage downloadPage;

    @ApiModelProperty(value = "参数条件")
    private SqlParam sqlParam;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "数据处理")
    private DataProcess dataProcess;

    public Fragmentation(BiFunction<SqlParam, DownloadPage, List<T>> function, SqlParam sqlParam, DownloadPage downloadPage, String filePath) {
        this.function = function;
        this.sqlParam = sqlParam;
        this.downloadPage = downloadPage;
        this.filePath = filePath;
    }

    /**
     * 开始执行
     */
    public void start() {
        long startTime = System.currentTimeMillis();
        LOGGER.info("开始执行任务分片");
        List<T> data = null;
        try {
            data = function.apply(sqlParam, downloadPage);
        } catch (Exception e) {
            LOGGER.error("获取数据出错：" + e.getMessage(), e);
        }
        if (CollectionUtils.isEmpty(data)) {
            LOGGER.info("数据为null");
            return;
        }
        boolean flag;
        if (Objects.nonNull(dataProcess)) {
            try {
                flag = dataProcess.apply(data, filePath);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                flag = false;
            }

        } else {
            try {
                flag = this.processDate(data, filePath);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                flag = false;
            }
        }
        LOGGER.info("执行任务分片结束，成功？{}", flag);
        // 打印结果
        println(startTime);
    }

    /**
     * 处理数据 -- 将数据写入excel
     * @param data
     * @param filePath
     * @return
     */
    private boolean processDate(List<T> data, String filePath) {
        // 获取文件
        File file = new File(filePath);
        // 是否创建请求头
        boolean createHead = false;
        if (!file.exists()) {
            // 文件不存在就创建
            try {
                file.createNewFile();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
                throw new IllegalArgumentException("文件创建失败" + e.getMessage());
            }
            createHead = true;
        }
        // 解析data
        List<List> list = processDate(data, createHead);
        // 写入文件
        return writeFile(file, list);
    }

    private static boolean writeFile(File file, List<List> list) {

        return CSVUtil.createCsvFile(list, file, true, true);
    }

    public static void main(String[] args) {

        File file = new File(PathUtil.getPath() + Constant.FilesDirectory.DOWNLOAD + "test.xlsx");
        int size = 4000;
        List<List> lists= new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            lists.add(Arrays.asList("a","b","c","d","a","b","c","d","1","3"));
        }
        long start = System.currentTimeMillis();
        writeFile(file, lists);
        long end = System.currentTimeMillis();
        System.out.println("finish1 time = " + (end - start) + "ms");

    }

    /**
     * 解析数据成特定格式
     * @param data
     * @param createHead  是否生成头部标题
     * @return
     */
    private List<List> processDate(List<T> data, boolean createHead) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        // 数据 --一行是一个list
        List<List> list = new ArrayList();
        List heads = null;
        if (createHead) {
            // 头部标题
            heads = new ArrayList<>();
            list.add(heads);
        }

        boolean flag = false;
        for (T bean : data) {
            Class<?> clazz = bean.getClass();
            List row = new ArrayList();// 一行数据
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {//向上循环  遍历父类
                Field[] field = clazz.getDeclaredFields();
                for (Field f : field) {
                    f.setAccessible(true);
                    // 判断该字段是否排除
                    if (Objects.nonNull(f.getAnnotation(ExcludeProperty.class))) {
                        continue;
                    }
                    // 获取excel头部描述 只需要一个，位于文档第一行
                    if (createHead && !flag) {
                        ApiModelProperty annotation = f.getAnnotation(ApiModelProperty.class);
                        if (Objects.nonNull(annotation)) {
                            heads.add(annotation.value());
                        } else {
                            heads.add(f.getName());
                        }
                    }
                    Object value = null;
                    try {
                        value = f.get(bean);
                    } catch (Exception e) {
                        value = "";
                    }
                    row.add(value);
                }
            }
            flag = true;
            list.add(row);
        }
        // 清除data
        data.clear();
        return list;
    }

    private void println(long startTime) {
        System.out.println("# ==========任务分片处理情况============ #");
        System.out.println("# sqlParam -> " + sqlParam.toString() + " #");
        System.out.println("# downloadPage -> " + downloadPage.toString() + " #");
        System.out.println("# filePath -> " + filePath + " #");
        System.out.println("# 耗时 -> " + (System.currentTimeMillis() - startTime) + "ms #" );
        System.out.println("# ================================== #");
    }

    public void close() {
        this.function = null;
        this.sqlParam = null;
        this.downloadPage = null;
        this.filePath = null;
        this.dataProcess = null;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public DataProcess getDataProcess() {
        return dataProcess;
    }

    public void setDataProcess(DataProcess dataProcess) {
        this.dataProcess = dataProcess;
    }
}
