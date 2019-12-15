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
 * 菜单表
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_menu")
@ApiModel(value="Menu对象", description="菜单表")
public class Menu extends BaseBean {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "父菜单id")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "url地址")
    private String url;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;


}
