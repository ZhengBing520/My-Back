package com.zb.sys.download;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

/**
 * Created by bzheng on 2019/12/13.
 * 任务后置处理器 -- 任务来直接执行
 */
public class TaskPostprocessorpostImpl implements TaskPostprocessorpost {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    RedisTemplate<String, String> redisTemplate;

    public TaskPostprocessorpostImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean finish(Task task) {
        try {
            consumerFinish(task, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }

        return true;
    }

    @Override
    public boolean exception(Task task, String errorMsg) {
        try {
            consumerException(task, errorMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }

        return true;
    }

    /**
     * 消费异常任务
     */
    protected void consumerException(Task task, String errorMsg) {
        try {
            // 删除key
            redisTemplate.delete(TaskDispatch.getRedisKey(task.getTaskId()));
            // 执行完成操作
            Optional.ofNullable(task.getException()).map(biConsumer -> {
                biConsumer.accept(task.getTaskId(), errorMsg);
                return true;
            }).orElseGet(() -> {
                logger.info("没有异常处理器");
                return false;
            });
            logger.info("任务id={}，的任务执行异常：{}。", task.getTaskId(), errorMsg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            // 释放任务资源
            task.close();
        }
    }

    /**
     * 消费完成的任务
     */
    protected void consumerFinish(Task task, String errorMsg) {
        try {
            // 删除key
            redisTemplate.delete(TaskDispatch.getRedisKey(task.getTaskId()));
            // 执行完成操作
            Optional.ofNullable(task.getAfter()).map(biConsumer -> {
                biConsumer.accept(task.getTaskId(), task.getFilePath());
                return true;
            }).orElseGet(() -> {
                logger.info("没有后置处理器");
                return false;
            });
            logger.info("任务id={}，的任务执行完成。", task.getTaskId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            // 释放任务资源
            task.close();
        }
    }
}
