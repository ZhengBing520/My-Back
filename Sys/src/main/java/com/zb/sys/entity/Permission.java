package com.zb.sys.entity;

import com.zb.rdb.bean.BaseBean;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色与权限关系表
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_permission")
@ApiModel(value="Permission对象", description="角色与权限关系表")
public class Permission extends BaseBean {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "关联角色id")
    private Long roleId;


}
