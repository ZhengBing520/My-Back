package com.zb.sys.service.impl;

import com.zb.sys.entity.Permission;
import com.zb.sys.mapper.PermissionMapper;
import com.zb.sys.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与权限关系表 服务实现类
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
