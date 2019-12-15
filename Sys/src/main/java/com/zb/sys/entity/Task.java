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
 * 系统任务处理表
 * </p>
 *
 * @author bzheng
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_task")
@ApiModel(value="Task对象", description="系统任务处理表")
public class Task extends BaseBean {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "关联机构编码")
    private String orgCode;

    @ApiModelProperty(value = "任务名称")
    private String name;

    @ApiModelProperty(value = "任务状态(1:等待中;2:执行中;3:任务被终止;4:已完成;5:执行失败)")
    private Integer status;

    @ApiModelProperty(value = "任务类型(1:普通任务;2:文件类型任务)")
    private Integer type;

    @ApiModelProperty(value = "任务处理进度")
    private Float percent;

    @ApiModelProperty(value = "任务信息(失败时记录失败原因)")
    private String message;

    @ApiModelProperty(value = "失败堆栈信息(失败时后台异常堆栈信息)")
    private String stackMessage;

    @ApiModelProperty(value = "任务开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "任务完成时间")
    private LocalDateTime finishTime;

    @ApiModelProperty(value = "文件路径(如果是生成文件的任务,存储的是文件路径;可以存储多个,以;分割)")
    private String filePaths;


}
