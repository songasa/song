package com.zut.service_parking.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2022-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "用户ID，默认自动生成UUID", example = "null")
    private String id;

    private String productNo;

    private String name;

    private String sex;

    private Integer status;

    private Integer flag;

    private String pwd;

    private String cardId;

    private String carId1;

    private String carId2;

    private String carId3;

    private Long money;

    @ApiModelProperty(value = "用户创建时间", example = "2022-01-01 10:10:10")
    @TableField(fill = FieldFill.INSERT)
    private Date userCreatetime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

}
