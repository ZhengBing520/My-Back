package com.zb.sys.service.impl;

import com.zb.sys.entity.UserRole;
import com.zb.sys.mapper.UserRoleMapper;
import com.zb.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色关联关系表 服务实现类
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
