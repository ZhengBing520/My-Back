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
 * 组织机构基础信息表
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_org")
@ApiModel(value="Org对象", description="组织机构基础信息表")
public class Org extends BaseBean {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "父组织id")
    private Long parentId;

    @ApiModelProperty(value = "组织层级编码(格式为1_2_3_,必须以_结尾)")
    private String code;

    @ApiModelProperty(value = "组织名称")
    private String name;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "备注")
    private String remark;


}
