package com.zb.sys.entity;

import com.zb.rdb.bean.BaseBean;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户基础信息表
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_user")
@ApiModel(value="User对象", description="用户基础信息表")
public class User extends BaseBean {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "类型(1:管理用户,2:企业用户)")
    private Integer type;

    @ApiModelProperty(value = "关联机构编码")
    private String orgCode;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "身份证号")
    private String cardNumber;

    @ApiModelProperty(value = "是否可用(0:禁用,1:可用)")
    private Integer status;


}
