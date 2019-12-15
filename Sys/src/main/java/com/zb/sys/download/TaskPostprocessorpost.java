package com.zb.sys.download;

/**
 * Created by bzheng on 2019/12/13.
  任务后置处理器 -- 处理已完成的任务
 */
public interface TaskPostprocessorpost {

    // 完成任务处理
    boolean finish(Task task);

    // 异常处理
    boolean exception(Task task, String errorMsg);

}
