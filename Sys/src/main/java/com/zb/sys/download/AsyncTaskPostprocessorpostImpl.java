package com.zb.sys.download;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.BiConsumer;

/**
 * Created by bzheng on 2019/12/13.
 * 异步任务后置处理器 -- 将任务加入队列，用线程执行队列
 */
public class AsyncTaskPostprocessorpostImpl extends TaskPostprocessorpostImpl{

    private static Logger logger = LoggerFactory.getLogger(AsyncTaskPostprocessorpostImpl.class);

    private RedisTemplate<String, String> redisTemplate;
    // 上限值
    private int maxSize = 2000;
    // 任务储存器 -- 存放所有已完成任务
    private BlockingQueue<Node> taskQueue;


    public AsyncTaskPostprocessorpostImpl(int maxSize, RedisTemplate<String, String> redisTemplate) {
        super(redisTemplate);
        this.maxSize = maxSize;
        taskQueue = new ArrayBlockingQueue(maxSize);
        this.redisTemplate = redisTemplate;
        startExecuteTaskTask();
    }

    /**
     * 开始执行任务
     */
    private void startExecuteTaskTask() {
        Runnable runnable = () -> {
            while (true) {
                Node node = null;
                try {
                    // 队列没有数据就阻塞
                    node = taskQueue.take();
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
                if (Objects.nonNull(node)) {
                    // 处理任务
                    process(node);
                }
            }
        };
        new Thread(runnable).start();
    }

    private void process(Node node) {
        try {
            // 消费任务 -> 可以扩展成多线程处理，但是没必要为了导出功能浪费资源
            node.getProcess().accept(node.getTask(), node.getMsg());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
          node.close();
        }
    }

    @Override
    public boolean finish(Task task) {
        // 拼装成node
        Node node = new Node(task, null, this::consumerFinish);
        // 加入队列
        return this.taskQueue.offer(node);
    }

    @Override
    public boolean exception(Task task, String errorMsg) {
        // 拼装成node
        Node node = new Node(task, errorMsg, this::consumerException);
        // 加入队列
        return this.taskQueue.offer(node);
    }

    /**
     * 定义异步消费任务节点
     */
    private class Node {

        private Task task;

        private String msg;

        private BiConsumer<Task,String> process;

        private Node(Task task, String msg, BiConsumer<Task,String> process) {
            this.task = task;
            this.msg = msg;
            this.process = process;
        }

        public Task getTask() {
            return task;
        }

        public String getMsg() {
            return msg;
        }

        public BiConsumer<Task, String> getProcess() {
            return process;
        }

        public void close() {
            this.task = null;
            this.msg = null;
            this.process = null;
        }
    }
}
