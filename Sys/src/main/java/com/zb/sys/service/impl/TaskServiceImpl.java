package com.zb.sys.service.impl;

import com.zb.sys.entity.Task;
import com.zb.sys.mapper.TaskMapper;
import com.zb.sys.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统任务处理表 服务实现类
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Service
public class TaskServiceImpl extends BaseServiceImpl<TaskMapper, Task> implements ITaskService {

}
