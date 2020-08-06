package com.zb.sys.download;

import com.zb.base.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.BiConsumer;

/**
 * Created by bzheng on 2019/12/4.
 * 任务调度
 */
@Component
public class TaskDispatch {

    // 任务执行器
    private TaskExecute taskExecute;

    private static Logger logger = LoggerFactory.getLogger(TaskDispatch.class);

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private void setRedisConnectionFactory(RedisConnectionFactory  redisRedisConnectionFactory) {
        redisTemplate = RedisUtil.newString_StringRedisTemplate(redisRedisConnectionFactory);
    }

    // 同时执行任务的数量
    private static int taskSize = 5;

    // 上限值
    private static int maxSize = 2000;
    // 任务储存器 -- 1.存放所有任务；2.顺序存放。使用一个有界阻塞队列，防止堆溢出
    private static BlockingQueue<Task> taskQueue = new ArrayBlockingQueue(maxSize);

    // redis前缀
    private static String redisPrefix = Constant.RedisNamespace.REDIS_TASK_ID;

    @PostConstruct
    // 初始化任务调度
    private void init() {
        // 成员变量
        initVariable();
        initRunable();
    }


    private void initVariable() {
        taskExecute = new TaskExecute(taskSize, maxSize, redisTemplate);
    }

    private void initRunable() {
        // 执行任务分配
        Runnable runnable = () -> {
            logger.info("执行任务分配");
            while (true) {
                // 判断任务执行器中的任务是否满了
                taskExecute.tryAddTask();
                Task task = null;
                try {
                    // 队列没有数据就阻塞
                    task = taskQueue.take();
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
                if (Objects.nonNull(task)) {
                    taskExecute.addTask(task);
                }
            }
        };
        new Thread(runnable).start();
        new Thread(taskExecute).start();
    }

    /**
     * 添加任务
     *
     * @param task
     */
    public void addTask(Task task) {
        Assert.notNull(task, "任务不能为空");
        String taskId = task.getTaskId();
        if (StringUtils.isBlank(taskId)) {
            throw new IllegalArgumentException("任务id不能为空");
        }
        if (!redisTemplate.opsForValue().setIfAbsent(getRedisKey(taskId), task.getFilePath())) {
            throw new IllegalArgumentException("任务id 为【 " + taskId + " 】的任务已存在");
        }
        boolean offer = taskQueue.offer(task);
        if (!offer) {
            redisTemplate.delete(getRedisKey(taskId));
            throw new IllegalArgumentException("系统任务达到上限，上限值为【" + maxSize + "】");
        }
        logger.info("添加任务成功，任务id为【{}】", task.getTaskId());
    }

    /**
     * 取消任务
     */
    public void cancel(String taskId) {
        try {
            // 用的redis，直接删除key
            Boolean delete = redisTemplate.delete(getRedisKey(taskId));
            logger.info("取消任务，任务id={},成功？{}", taskId, delete);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }


    /**
     * 拼凑redis的key
     *
     * @param key
     * @return
     */
    public static String getRedisKey(String key) {
        return redisPrefix + key;
    }

    /**
     * 任务执行器
     */
    private class TaskExecute implements Runnable {

        // 任务后置处理器 -- 处理以完成任务 -> 可以选同步，可以选异步
        private TaskPostprocessorpost taskPostprocessorpost;

        // 任务数据 -- 可以同时执行
        private final List<Task> data;

        // 最大可同时执行任务数量
        private int taskSize;

        TaskExecute(int taskSize, int maxSize, RedisTemplate<String, String> redisTemplate) {
            this.taskSize = taskSize;
            this.data = new ArrayList<>(taskSize);
            this.taskPostprocessorpost = new AsyncTaskPostprocessorpostImpl(maxSize, redisTemplate);
        }

        public void addTask(Task task) {
            // task初始化
            try {
                task.init();
                data.add(task);
            } catch (Exception e) {
                logger.error("任务初始化失败：" + e.getMessage(), e);
            }

        }

        /**
         * 任务执行器中的任务是否满了
         *
         * @return
         */
        private boolean isFull() {
            return data.size() >= maxSize;
        }

        /**
         * 任务执行器中的是否有任务
         *
         * @return
         */
        private boolean isEmpty() {
            return data.isEmpty();
        }

        /**
         * 尝试添加数据
         */
        private void tryAddTask() {
            if (this.isFull()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
                tryAddTask();
            }
            return;
        }

        /**
         * 尝试执行任务
         */
        public void tryExecuteTask() {
            if (this.isEmpty()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
                tryExecuteTask();
            }
            return;
        }

        /**
         * 任务完成
         */
        public void finish(Task task) {
            taskPostprocessorpost.finish(task);
        }

        /**
         * 任务异常
         */
        public void exception(Task task, String errorMsg) {
            taskPostprocessorpost.exception(task, errorMsg);
        }

        @Override
        public void run() {
            while (true) {
                tryExecuteTask();
                logger.info("开始执行任务");
                // 倒序 -> 保证在集合里面的任务能被执行
                for (int i = data.size() - 1; i >= 0; i--) {
                    Task task = data.get(i);
                    try {
                        // 判断当前任务是否取消
                        if (Objects.isNull(redisTemplate.opsForValue().get(getRedisKey(task.getTaskId())))) {
                            logger.info("任务id = {}，已被取消", task.getTaskId());
                            task.close();
                            // 删除当前任务
                            data.remove(i);
                            continue;
                        }
                    } catch (Exception e) {
                        logger.info(e.getMessage(), e);
                    }

                    // 判断当前任务的状态
                    if (task.isFinish()) {
                        // 执行完成操作
                        this.finish(task);
                        // 删除当前任务
                        data.remove(i);
                        continue;
                    }
                    // 未完成
                    try {
                        boolean execute = task.execute();
                        // 打印进度
                        task.println();
                    } catch (Exception e) {
                        // 执行异常操作
                        logger.error(e.getMessage(), e);
                        this.exception(task, e.getMessage());
                        data.remove(i);
                    }
                }
            }
        }
    }

    private TaskDispatch() {
    }
}
