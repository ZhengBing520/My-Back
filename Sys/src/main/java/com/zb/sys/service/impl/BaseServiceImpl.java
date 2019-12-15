package com.zb.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zb.base.utils.RedisUtil;
import com.zb.sys.download.*;
import com.zb.sys.download.utils.CSVUtil;
import com.zb.sys.download.utils.ExcelProcessUtil;
import com.zb.sys.download.utils.ExcelUtil;
import com.zb.sys.download.utils.PathUtil;
import com.zb.sys.params.BaseParam;
import com.zb.sys.service.BaseService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.util.*;

/**
 * Created by bzheng on 2019/12/10.
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisConnectionFactory(RedisConnectionFactory redisConnectionFactory) {
        this.redisTemplate = RedisUtil.newString_StringRedisTemplate(redisConnectionFactory);
    }

    @Autowired
    TaskDispatch taskDispatch;

    @Override
    public void export(Wrapper<T> wrapper) {
        // 创建任务id
        Long taskId = redisTemplate.opsForValue().increment(Constant.RedisNamespace.REDIS_INCREMENT_TASK_ID);
        // 创建任务
        Task<T> task = Task.newInstance(this, getBaseParam(wrapper), taskId.toString());
        // 成功之后，导出成excel
        task.setAfter(this::after);
        // 将任务添加进任务调度
        taskDispatch.addTask(task);
    }

    @Override
    public long count(SqlParam sqlParam) {
        BaseParam<T> param = (BaseParam<T>) sqlParam;
        return getBaseMapper().selectCount(param.getQueryWrapper());
    }

    @Override
    public List<T> page(SqlParam sqlParam, DownloadPage downloadPage) {
        BaseParam<T> param = (BaseParam<T>) sqlParam;
        IPage<T> page = getBaseMapper().selectPage(new Page<>(downloadPage.getCurrent(), downloadPage.getSize()), param.getQueryWrapper());
        if (Objects.isNull(page)) {
            return Collections.emptyList();
        }
        return page.getRecords();
    }


    protected BaseParam<T> getBaseParam(Wrapper<T> queryWrapper) {
        return new BaseParam<>(queryWrapper);
    }

    /**
     * 默认的后置处理
     * @param taskId
     * @param csvPath
     */
    protected void after(String taskId, String csvPath) {
        // 生成excel路径
        String excelPath = PathUtil.getPath() + Constant.FilesDirectory.DOWNLOAD + UUID.randomUUID().toString() + ".xlsx";
        // 转成excel文档
        logger.info("taskId = {}, csvPath = {}, excelPath = {}", taskId, csvPath, excelPath);
        // 将CSV文件转成数据
        List<List> lists = null;
        try {
            lists = CSVUtil.readCsv(csvPath);
            XSSFWorkbook xssfWorkbook = ExcelUtil.exportExcel_2007(lists, null);
            ExcelProcessUtil.exportFile(xssfWorkbook, excelPath, "zb123");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            // 删除文件
            try {
                // 清除list数据
                Optional.ofNullable(lists).map(data -> {
                    data.clear();
                    return true;
                }).orElse(false);
                File file = new File(csvPath);
                file.delete();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

        }

    }
}
