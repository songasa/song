package com.zut.service_parking.utils.config.exceptionHandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//生成get，set方法
@AllArgsConstructor//生成有参构造方法
@NoArgsConstructor//生成无参构造方法
public class ZutException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;

    @Override
    public String toString() {
        return "ZutException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }


}
