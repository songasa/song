package com.zut.service_parking.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2022-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ParkingDetails对象", description="")
public class ParkingDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "停车位编号，默认自动生成UUID", example = "null")
    private String parkingNo;

    @ApiModelProperty(value = "停车位所有人手机号", example = "12345678910")
    private String ownerProductNo;

    @ApiModelProperty(value = "查询创建时间", example = "2022-01-01 10:10:10")
    @TableField(fill = FieldFill.INSERT)
    private Date parkingCreatetime;

    @ApiModelProperty(value = "查询失效时间", example = "2022-12-01 10:10:10")
//    @TableField(fill = FieldFill.UPDATE)
    private Date parkingExpiretime;

    private String parkingDetailsLongitude;

    private String parkingDetailsLatitude;

    private String parkingDetailsNote;

    private String price;

    private String parkingCounts;

    private String parkingTimes;

    private String earnings;

    private long appoint;

    private String adcode;

    private String distince;

    private String count;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

}
