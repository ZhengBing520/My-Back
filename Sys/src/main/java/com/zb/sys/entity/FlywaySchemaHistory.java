package com.zb.sys.entity;

import com.zb.rdb.bean.BaseBean;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author bzheng
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="FlywaySchemaHistory对象", description="")
public class FlywaySchemaHistory extends BaseBean {

    private static final long serialVersionUID=1L;

    private Integer installedRank;

    private String version;

    private String description;

    private String type;

    private String script;

    private Integer checksum;

    private String installedBy;

    private LocalDateTime installedOn;

    private Integer executionTime;

    private Boolean success;

    public static void main(String[] args) {
        System.out.println("[29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 28.0, 29.0, 28.0, 28.0, 28.0, 29.0, 28.0, 28.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0, 29.0]".length());
        System.out.println("[4.006, 4.006, 4.006, 4.006, 4.007, 4.009, 4.007, 4.007, 4.004, 4.002, 4.001, 4.006, 4.004, 4.001, 4.002, 4.001, 4.003, 4.002, 4.002, 4.002, 4.006, 4.002, 4.001, 4.002, 4.004, 3.994, 4.003, 4.002, 4.002, 4.001, 4.003, 4.001, 4.003, 4.002, 4.001, 4.001, 4.002, 4.002, 4.001, 4.001, 4.005, 4.005, 4.003, 4.001, 4.003, 4.001, 4.002, 4.001, 4.005, 4.002, 4.002, 4.002, 4.004, 4.003, 4.002, 4.002, 4.008, 4.006, 4.009, 4.006, 4.007, 4.006, 4.008, 4.006, 4.007, 4.009, 4.007, 4.008, 4.008, 4.007, 4.008, 4.008, 4.008, 4.007, 4.007, 4.006, 4.006, 4.006, 4.007, 4.006, 4.007, 4.007, 4.007, 4.007, 4.007, 4.007, 4.007, 4.008, 4.007, 4.007, 4.006, 4.006, 4.007, 4.008, 4.006, 4.007]".length());
    }

}
