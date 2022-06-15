package com.zut.service_parking.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "User查询对象", description = "用户查询对象封装")
@Data
public class UserQuery {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private String id;

    @ApiModelProperty(value = "手机号")
    private String productNo;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "状态正常",example = "1")
    private Integer status;

    @ApiModelProperty(value = "是否是管理员",example = "1")
    private Integer flag;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "身份证号")
    private String cardId;

    @ApiModelProperty(value = "车牌号1")
    private String carId1;

    private String carId2;

    private String carId3;

    @ApiModelProperty(value = "余额")
    private Long money;

}
