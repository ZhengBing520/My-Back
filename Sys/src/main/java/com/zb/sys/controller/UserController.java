package com.zb.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zb.base.define.MessageDefine;
import com.zb.base.message.JsonMessage;
import com.zb.sys.entity.User;
import com.zb.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户基础信息表 前端控制器
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@RestController
@Api(value = "用户管理 Client Restful API ", description = "用户管理 Client Restful API ", protocols = "application/json")
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;


    /**
     * 查询用户信息管理分页
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户信息管理分页", notes = "查询用户信息管理分页")
    @ApiResponse(code = 200, message = "查询用户信息管理分页")
    public JsonMessage<IPage<User>> page(
            @ApiParam(value = "用户名")
            @RequestParam(value = "username", required = false) String username,
            @ApiParam(value = "分页参数(页数)", example = "1")
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @ApiParam(value = "分页参数(页大小)", example = "20")
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.like(true,User::getUsername, username);
        IPage<User> page = userService.page(new Page<>(pageNum, pageSize), wrapper);
        return JsonMessage.success(page);
    }

    /**
     * 导出用户信息管理列表
     *
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ApiOperation(value = "导出用户信息管理列表", notes = "导出用户信息管理列表")
    @ApiResponse(code = 200, message = "导出用户信息管理列表")
    public JsonMessage export(
            @ApiParam(value = "用户名")
            @RequestParam(value = "username", required = false) String username
    ) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.like(false, User::getUsername, username);
        userService.export(wrapper);
        return MessageDefine.SUCCESS_EXPORT.toJsonMessage(true);
    }


}
