package com.zb.generator.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.IGNORED;

/**
 * Created by Administrator on 2017/4/11.
 */
@Getter
@Setter
public class BaseBean extends SuperBaseBean {

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            locale = "zh",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(updateStrategy = IGNORED)
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;

    @TableField(updateStrategy = IGNORED)
    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            locale = "zh",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人id")
    private Long updateUserId;

    @ApiModelProperty(value = "更新人姓名")
    private String updateUserName;

    @ApiModelProperty(value = "创建ip地址")
    private String createIp;

    @ApiModelProperty(value = "更新ip地址")
    private String updateIp;

}
