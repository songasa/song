package com.zut.service_parking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zut.service_parking.entity.ParkingDetails;
import com.zut.service_parking.entity.ParkingLogs;
import com.zut.service_parking.entity.vo.ParkingDetailsQuery;
import com.zut.service_parking.entity.vo.ParkingLogsQuery;
import com.zut.service_parking.service.ParkingDetailsService;
import com.zut.service_parking.service.ParkingLogsService;
import com.zut.service_parking.utils.commonutils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author song
 * @since 2022-03-18
 */
@RestController
@CrossOrigin //解决跨域问题
@Slf4j
@RequestMapping("/service_parking/parking-logs")
public class ParkingLogsController {

    @Autowired//注入Service 默认按照类型
    //@Qualifier：根据名称进行注入
    private ParkingLogsService parkingLogsService;

    //1.添加租用和出租日志的方法
    @ApiOperation("添加租用和出租日志的方法")
    @PostMapping
    public R addParkingLogs(
            @ApiParam(name = "parkingLogs", value = "日志对象", required = true)
            @RequestBody ParkingLogs parkingLogs) {
        boolean save = parkingLogsService.save(parkingLogs);
        if (save) {
            return R.ok();
        } else {
            return R.error().message("请选择租用的起止时间！");
        }
    }

    //2.条件查询带分页查询 租用订单
    @ApiOperation("条件查询带分页查询 租用订单")
    @PostMapping("parkingLogsFindByProductNo/{current}/{limit}/{productNo}/{status}")
    public R parkingLogsFindByProductNo(@PathVariable long current, @PathVariable long limit,
                                        @PathVariable String productNo,
                                        @PathVariable long status) {
        //创建对象
        Page<ParkingLogs> page = new Page<>(current, limit);
        //构造条件
        QueryWrapper<ParkingLogs> queryWrapper = new QueryWrapper<>();
        //wrapper.多条件组合查询
        //判断条件值是否为空
        //column为字段名称，别传对象名，并未做驼峰转换
        if (!StringUtils.isEmpty(productNo)) {
            //构造条件
            queryWrapper.eq("product_no", productNo);
        }
        //状态为0进行  1完成
        queryWrapper.eq("status",status);
        //排序
        queryWrapper.orderByDesc("log_createtime","log_updatetime");

        //调用方法使用条件查询
        IPage<ParkingLogs> page1 = parkingLogsService.page(page,queryWrapper);
        long total = page1.getTotal();//记录数
        List<ParkingLogs> records = page1.getRecords();//集合
        return R.ok().data("total", total).data("records", records);
    }

    //3.根据日志表ID set status = 1 结束 收益 earning
    @ApiOperation("根据日志表ID set status = 1 结束 收益 earning")
    @PostMapping("setEarningByID/{ID}/{earning}/{actualtime}")
    public R setEarningByID(
            @ApiParam(name = "ID", value = "编号", required = true)
            @PathVariable String ID,
            @ApiParam(name = "earning", value = "收益", required = true)
            @PathVariable String earning,
            @ApiParam(name = "actualtime", value = "实际结束时间", required = true)
            @PathVariable String actualtime
    ) {
        ParkingLogs parkingLogs = new ParkingLogs();
        parkingLogs.setEarning(earning);
        parkingLogs.setActualtime(actualtime);
        System.out.println(parkingLogs.getActualtime());
        parkingLogs.setStatus(1L);
        parkingLogs.setId(ID);
        parkingLogsService.updateById(parkingLogs);
        return R.ok();
    }

    //4.根据日志表ID 修改预计结束时间，续时
    @ApiOperation("根据日志表ID 修改预计结束时间，续时")
    @PostMapping("setReTimeByID/{ID}/{parkingUsetimeEnd}")
    public R setReTimeByID(
            @ApiParam(name = "ID", value = "编号", required = true)
            @PathVariable String ID,
            @ApiParam(name = "parkingUsetimeEnd", value = "续时时间", required = true)
            @PathVariable String parkingUsetimeEnd
    ) throws ParseException {
        ParkingLogs parkingLogs = new ParkingLogs();
        parkingLogs.setParkingUsetimeEnd(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parkingUsetimeEnd));
        parkingLogs.setId(ID);
        parkingLogsService.updateById(parkingLogs);
        return R.ok();
    }

    //5.根据停车位编号修改日志表信息
    @ApiOperation("根据停车位编号修改停车位信息")
    @PostMapping("updateLogsByParkingNo/{parkingNo}")
    public R updateLogsByParkingNo(
            @ApiParam(name = "parkingNo", value = "停车位编号", required = true)
            @PathVariable String parkingNo,
            @ApiParam(name = "ParkingLogs", value = "停车位信息", required = true)
            @RequestBody(required = true) ParkingLogs parkingLogs
    ) {
        String ID = "";
        //创建对象
        Page<ParkingLogs> page = new Page<>(1, 1);
        //构造条件
        QueryWrapper<ParkingLogs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parking_no",parkingNo);
        queryWrapper.eq("status",1L);
        queryWrapper.eq("log_level",1L);
        IPage<ParkingLogs> page1 = parkingLogsService.page(page,queryWrapper);
        List<ParkingLogs> records = page1.getRecords();//集合
        for (ParkingLogs record : records) {
            ID = record.getId();
        }
        System.out.println(records);
        parkingLogs.setId(ID);
        parkingLogsService.updateById(parkingLogs);
        return R.ok();
    }

    //6.根据日志表ID 状态为1完成 退订
    @ApiOperation("根据日志表ID 状态为1完成 退订")
    @PostMapping("setStatusByID/{ID}")
    public R setStatusByID(
            @ApiParam(name = "ID", value = "编号", required = true)
            @PathVariable String ID
    ) {
        ParkingLogs parkingLogs = new ParkingLogs();
        parkingLogs.setId(ID);
        parkingLogs.setStatus(1L);
        parkingLogsService.updateById(parkingLogs);
        return R.ok();
    }

    //7.条件查询带分页查询 停车位订单
    @ApiOperation("条件查询带分页查询 停车位订单")
    @PostMapping("parkingLogsFindByParkingNo/{current}/{limit}/{parkingNo}")
    public R parkingLogsFindByParkingNo(@PathVariable long current, @PathVariable long limit,
                                        @PathVariable String parkingNo) {
        //创建对象
        Page<ParkingLogs> page = new Page<>(current, limit);
        //构造条件
        QueryWrapper<ParkingLogs> queryWrapper = new QueryWrapper<>();
        //wrapper.多条件组合查询
        //判断条件值是否为空
        //column为字段名称，别传对象名，并未做驼峰转换
        if (!StringUtils.isEmpty(parkingNo)) {
            //构造条件
            queryWrapper.eq("parking_no", parkingNo);
            queryWrapper.eq("log_level", "2");
        }
        //排序
        queryWrapper.orderByDesc("log_createtime","log_updatetime");

        //调用方法使用条件查询
        IPage<ParkingLogs> page1 = parkingLogsService.page(page,queryWrapper);
        long total = page1.getTotal();//记录数
        List<ParkingLogs> records = page1.getRecords();//集合
        return R.ok().data("total", total).data("records", records);
    }

}

