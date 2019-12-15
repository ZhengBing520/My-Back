package com.zb.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zb.base.utils.RedisUtil;
import com.zb.sys.download.Constant;
import com.zb.sys.download.Task;
import com.zb.sys.entity.User;
import com.zb.sys.mapper.UserMapper;
import com.zb.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 * 用户基础信息表 服务实现类
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {


}
