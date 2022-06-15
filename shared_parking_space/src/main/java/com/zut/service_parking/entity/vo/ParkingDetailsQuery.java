package com.zut.service_parking.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author song
 */
@ApiModel(value = "ParkingDetails查询对象", description = "停车位查询对象封装")
@Data
public class ParkingDetailsQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "停车位编号,模糊查询")
    private String parkingNo;

    @ApiModelProperty(value = "所属人手机号")
    private String ownerProductNo;

    @ApiModelProperty(value = "查询创建时间", example = "2022-01-01 10:10:10")
    private String parkingCreatetime;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询失效时间", example = "2022-12-01 10:10:10")
    private String parkingExpiretime;

    @ApiModelProperty(value = "是否被预约", example = "0")
    private long appoint;

    @ApiModelProperty(value = "省市区编码", example = "410781")
    private long adcode;

    @ApiModelProperty(value = "剩余车位", example = "0")
    private long count;
}
