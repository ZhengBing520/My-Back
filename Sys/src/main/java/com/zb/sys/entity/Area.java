package com.zb.sys.entity;

import com.zb.rdb.bean.BaseBean;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zb.rdb.bean.SuperBaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 行政地区表
 * </p>
 *
 * @author bzheng
 * @since 2019-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_area")
@ApiModel(value="Area对象", description="行政地区表")
public class Area extends SuperBaseBean {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "地区名")
    private String areaName;

    @ApiModelProperty(value = "父级编码")
    private String parentCode;

    @ApiModelProperty(value = "地区编码")
    private String areaCode;


}
