package com.zb.sys.download;

import com.zb.sys.download.utils.PathUtil;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by bzheng on 2019/12/4.
 * 导出任务
 */
public class Task<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    DownloadService<T> service;

    @ApiModelProperty(value = "任务id")
    private String taskId;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "参数")
    private SqlParam sqlParam;

    @ApiModelProperty(value = "分页对象")
    private DownloadPage downloadPage;

    @ApiModelProperty(value = "任务分片")
    private Fragmentation fragmentation;

    @ApiModelProperty(value = "数据处理")
    private DataProcess dataProcess;

    @ApiModelProperty(value = "任务完成之后的操作")
    private BiConsumer<String, String> after; // param1:任务id，param2：CSV文件路径

    @ApiModelProperty(value = "发生异常之后的操作")
    private BiConsumer<String, String> exception;// param1:任务id，param2：异常信息

    private Task(DownloadService service, String taskId, SqlParam sqlParam) {
        this.service = service;
        this.taskId = taskId;
        this.sqlParam = sqlParam;
        // 生成文件路径
        this.filePath = PathUtil.getPath() + Constant.FilesDirectory.DOWNLOAD + UUID.randomUUID().toString() + ".csv";
    }

    public static <T> Task<T> newInstance(DownloadService service, SqlParam sqlParam, String taskId ) {
        return new Task(service, taskId, sqlParam);
    }

    /**
     * 初始化方法
     */
    public void init() {
        if (Objects.isNull(service)) {
            throw new IllegalArgumentException("执行数据库连接不能为null");
        }
        long count = service.count(sqlParam);
        if (0 == count) {
            throw new IllegalArgumentException("没有数据");
        }

        // 初始化page -- 可以指定size大小
        downloadPage = new DownloadPage(count);
    }

    /**
     * 查询是否完成
     *
     * @return
     */
    public boolean isFinish() {
        // 当前页 比总页数还大, 相等的时候说明还有最后一页等待执行
        return downloadPage.getCurrent() > downloadPage.getTotalPage();
    }

    /**
     * 执行任务
     *
     * @return
     */
    public boolean execute() {
        if (isFinish()) {
            LOGGER.info("任务已完成");
            return true;
        }

        // 生成分片
        BiFunction<SqlParam, DownloadPage, List<T>> function = this::query;
        this.fragmentation = new Fragmentation(function, sqlParam, downloadPage, filePath);
        fragmentation.setDataProcess(this.dataProcess);
        try {
            fragmentation.start();
            // 准备下一分片的数据
            nextFragmentation();
        } catch (Exception e) {
            LOGGER.error("启动分片失败：" + e.getMessage(), e);
            return false;
        }

        return true;
    }

    /**
     * 打印当前任务进度
     */
    public void println() {
        System.out.println("####### 任务id=" + taskId + "的任务进度 ######");
        double schedule = new BigDecimal((downloadPage.getCurrent() - 1) * 100 / downloadPage.getTotalPage()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("任务完成进度：" + schedule + "%");
        System.out.println("####### ------------------------ ######");
    }

    /**
     * 准备下一分片的数据 -- 将当前页++
     */
    private void nextFragmentation() {
        downloadPage.next();
    }

    /**
     * 查询数据
     *
     * @param sqlParam 条件
     * @param downloadPage 分页参数
     * @return
     */
    public List<T> query(SqlParam sqlParam, DownloadPage downloadPage) {

        return service.page(sqlParam, downloadPage);
    }

    /**
     * 完成之后获取文件路径
     *
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    public String getTaskId() {
        return taskId;
    }

    public void close() {
        this.service = null;
        this.taskId = null;
        this.filePath = null;
        this.sqlParam = null;
        this.downloadPage = null;
        this.fragmentation.close();
        this.fragmentation = null;
        this.dataProcess = null;
        this.after = null;
        this.exception = null;
    }


    public DataProcess getDataProcess() {
        return dataProcess;
    }

    public void setDataProcess(DataProcess dataProcess) {
        this.dataProcess = dataProcess;
    }

    public BiConsumer<String, String> getAfter() {
        return after;
    }

    public void setAfter(BiConsumer<String, String> after) {
        this.after = after;
    }

    public BiConsumer<String, String> getException() {
        return exception;
    }

    public void setException(BiConsumer<String, String> exception) {
        this.exception = exception;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        /*for (int i = list.size() - 1; i >= 0; i--) {
            String s = list.get(i);
            if (s.equals("aa")) {
                list.remove(i);
                list.add("cc");
            }
            System.out.println(s);
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            String s = list.get(i);
            System.out.println(s);
        } // OK */

    }
}
