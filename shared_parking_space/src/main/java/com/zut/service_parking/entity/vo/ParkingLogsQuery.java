package com.zut.service_parking.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "ParkingLogs查询对象", description = "日志表查询对象封装")
@Data
public class ParkingLogsQuery {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "停车位编号")
    private String parkingNo;

    @ApiModelProperty(value = "使用者手机号")
    private String productNo;

    @ApiModelProperty(value = "停车位使用开始时间", example = "2022-01-01 10:10:10")
    private Date parkingUsetimeBegin;

    @ApiModelProperty(value = "停车位使用结束时间", example = "2022-12-01 10:10:10")
    private Date parkingUsetimeEnd;

    @ApiModelProperty(value = "停车位使用实际结束时间", example = "2022-12-01 10:10:10")
    private String actualtime;

    @ApiModelProperty(value = "停车位所有人手机号")
    private String ownerProductNo;

    @ApiModelProperty(value = "停车位创建时间", example = "2022-01-01 10:10:10")
    private Date parkingsCreatetime;

    @ApiModelProperty(value = "停车位失效时间", example = "2022-12-01 10:10:10")
    private Date parkingsExpiretime;

    @ApiModelProperty(value = "日志类型(1是出租，2是租用)")
    private Long logLevel;

    @ApiModelProperty(value = "日志插入时间", example = "2022-01-01 10:10:10")
    private Date logCreatetime;

    @ApiModelProperty(value = "日志更新时间", example = "2022-12-01 10:10:10")
    private Date logUpdatetime;

    @ApiModelProperty(value = "状态(1结束，0进行)", example = "0")
    private Long status;

    @ApiModelProperty(value = "停车位地址")
    private String parkingDetailsNote;

    @ApiModelProperty(value = "停车位价格")
    private String price;

    @ApiModelProperty(value = "收益")
    private String earning;
}
