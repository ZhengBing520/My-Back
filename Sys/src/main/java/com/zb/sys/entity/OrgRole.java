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
 * 角色与机构关联关系表
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_org_role")
@ApiModel(value="OrgRole对象", description="角色与机构关联关系表")
public class OrgRole extends BaseBean {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "关联机构id")
    private Long orgId;

    @ApiModelProperty(value = "关联角色编码")
    private String roleCode;


}
