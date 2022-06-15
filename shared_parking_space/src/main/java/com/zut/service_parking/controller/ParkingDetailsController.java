package com.zut.service_parking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zut.service_parking.entity.ParkingDetails;
import com.zut.service_parking.entity.vo.ParkingDetailsQuery;
import com.zut.service_parking.mapper.ParkingDetailsMapper;
import com.zut.service_parking.service.ParkingDetailsService;
import com.zut.service_parking.utils.commonutils.R;
import com.zut.service_parking.utils.commonutils.util.Gd;
import com.zut.service_parking.utils.commonutils.util.MapUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-02-19
 */


//JSON传输，json-lib Jackson Gson Fastjson
@Slf4j
@RestController
@RequestMapping("/service_parking/parking-details")
@CrossOrigin
@Service
//@Bean 自定义 Component// ComponentScan
public class ParkingDetailsController {
    @Autowired//注入Service 默认按照类型
    //@Qualifier：根据名称进行注入
    private ParkingDetailsService parkingDetailsService;

    //private JdbcTemplate jdbcTemplate;

    //1.查询表所有内容
    @ApiOperation("查询表所有内容")
    @RequestMapping(value = "findAllParkingDetail", method = RequestMethod.GET)
    public R findAllParkingDetail() {
        List<ParkingDetails> list = parkingDetailsService.list(null);

//        异常
//        try{
//            int i = 10/0;
//        }catch (Exception e){
//            log.error(ExceptionUtil.getMessage(e));
//            throw new ZutException(20001,"执行了自定义异常处理");
//        }
//
        return R.ok().data("items", list);
    }

    //2.删除表中元素
    @CacheEvict(value = "parking", allEntries = true)
    @ApiOperation("删除表中元素")
//    @RequestMapping(value = "deleteParkingDetails", method = RequestMethod.GET)
    @DeleteMapping("/deleteParkingDetails/{parkingNo}")
    public R deleteParkingDetails(@PathVariable String parkingNo) {
        boolean b = parkingDetailsService.removeById(parkingNo);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //3.分页查询元素
    @ApiOperation("查询分页")
//    @RequestMapping(value = "page",method = RequestMethod.GET)
    @GetMapping("page")
    public R page(long current, long limit) {
        //创建Page对象
        Page<ParkingDetails> page = new Page<>(current, limit);
        IPage<ParkingDetails> page1 = parkingDetailsService.page(page, null);
        long total = page1.getTotal();//记录数
        List<ParkingDetails> records = page1.getRecords();//集合

        return R.ok().data("total", total).data("records", records);

        //因为COMMON类中R自定义方法返回为Map集合，也可以这样返回
//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("records",records);
//        return R.ok().data(map);
    }

    //4.条件查询带分页查询
    @ApiOperation("条件查询带分页查询")
    @PostMapping("parkingDetailsFindCondition/{current}/{limit}")
    //@PathVariable 保证当前传的数据为必需的且对应路径/{current}和/{limit}
    public R parkingDetailsFindCondition(@PathVariable long current, @PathVariable long limit,
                                         @RequestBody(required = false) ParkingDetailsQuery parkingDetailsQuery) {
        //创建对象
        Page<ParkingDetails> page = new Page<>(current, limit);
        //构造条件
        QueryWrapper<ParkingDetails> queryWrapper = new QueryWrapper<>();
        //wrapper.多条件组合查询
        //mybatis动态sql
        String parkingNo = parkingDetailsQuery.getParkingNo();
        String ownerProductNo = parkingDetailsQuery.getOwnerProductNo();
        String parkingCreatetime = parkingDetailsQuery.getParkingCreatetime();
        String parkingExpiretime = parkingDetailsQuery.getParkingExpiretime();
        //判断条件值是否为空
        //column为字段名称，别传对象名，并未做驼峰转换
        if (!StringUtils.isEmpty(parkingNo)) {
            //构造条件
            queryWrapper.eq("parking_no", parkingNo);
        }
        if (!StringUtils.isEmpty(ownerProductNo)) {
            //构造条件
            queryWrapper.eq("owner_product_no", ownerProductNo);
        }
        if (!StringUtils.isEmpty(parkingCreatetime)) {
            //构造条件
            queryWrapper.ge("parking_createtime", parkingCreatetime);
        }
        if (!StringUtils.isEmpty(parkingExpiretime)) {
            //构造条件
            queryWrapper.le("parking_expiretime", parkingExpiretime);
        }

        //排序
        queryWrapper.orderByDesc("parking_createtime");

        //调用方法使用条件查询
        IPage<ParkingDetails> page1 = parkingDetailsService.page(page, queryWrapper);
        long total = page1.getTotal();//记录数
        List<ParkingDetails> records = page1.getRecords();//集合
        return R.ok().data("total", total).data("records", records);
    }

    //5.添加停车位接口的方法
    @CacheEvict(value = "parking", allEntries = true)
    @ApiOperation("添加停车位接口的方法")
    @PostMapping
    public R addParkingDetails(
            @ApiParam(name = "parkingDetails", value = "停车位对象", required = true)
            @RequestBody ParkingDetails parkingDetails) {
        boolean save = parkingDetailsService.save(parkingDetails);
        if (save) {
            return R.ok().data("parkingNo",parkingDetails.getParkingNo())
                    .data("parkingCreatetime",parkingDetails.getParkingCreatetime());
        } else {
            return R.error();
        }
    }

    //6.根据停车位编号查询信息
    @Cacheable(value = "parking", key = "'getByParkingNo'+#parkingNo")
    @ApiOperation("根据停车位编号查询信息")
//    @RequestMapping(value = "getByNo/{no}",method = RequestMethod.GET)
    @GetMapping("getByParkingNo/{parkingNo}")
    public R getByParkingNo(@ApiParam(name = "parkingNo", value = "停车位编号", required = true)
                            @PathVariable String parkingNo) {
        ParkingDetails parking = parkingDetailsService.getById(parkingNo);
        return R.ok().data("parking", parking);
    }

    //7.根据停车位编号修改停车位信息
    @CacheEvict(value = "parking", allEntries = true)
    @ApiOperation("根据停车位编号修改停车位信息")
    @PostMapping("updateParkingDetailsByParkingNo/{parkingNo}")
    public R updateParkingDetailsByParkingNo(
            @ApiParam(name = "parkingNo", value = "停车位编号", required = true)
            @PathVariable String parkingNo,
            @ApiParam(name = "ParkingDetails", value = "停车位信息", required = true)
            @RequestBody(required = true) ParkingDetails parkingDetails
    ) {
        parkingDetails.setParkingNo(parkingNo);
        String[] gd = Gd.addressToGPS(parkingDetails.getParkingDetailsNote());
        parkingDetails.setParkingDetailsLongitude(gd[0]);
        parkingDetails.setParkingDetailsLatitude(gd[1]);
        parkingDetails.setAdcode(gd[2]);
        parkingDetailsService.updateById(parkingDetails);
        return R.ok();
    }

    //8.根据停车位地址信息返回 经纬度 adcode
    @CachePut(value = "parking_ll", key = "#address")
    @ApiOperation("根据停车位地址信息返回 经纬度 adcode")
    @PostMapping("parking_ll/{address}")
    public R parking_ll(
            @ApiParam(name = "address", value = "停车位地址", required = true)
            @PathVariable String address
    ) {
        String str = address.replace(",", "");
        String[] data = Gd.addressToGPS(str);
        return R.ok().data("longitude", data[0]).data("latitude", data[1]).data("address", str).data("adcode", data[2]);
    }

    //9.条件查询带分页查询 查询指定手机号
    @ApiOperation("条件查询带分页查询 查询指定手机号")
    @PostMapping("parkingDetailsFindByProductNo/{current}/{limit}/{ownerProductNo}")
    public R parkingDetailsFindByProductNo(@PathVariable long current, @PathVariable long limit,
                                           @PathVariable String ownerProductNo) {
        //创建对象
        Page<ParkingDetails> page = new Page<>(current, limit);
        //构造条件
        QueryWrapper<ParkingDetails> queryWrapper = new QueryWrapper<>();
        //column为字段名称，别传对象名，并未做驼峰转换
        queryWrapper.eq("owner_product_no", ownerProductNo);
        //排序
        queryWrapper.orderByDesc("parking_createtime");

        //调用方法使用条件查询
        IPage<ParkingDetails> page1 = parkingDetailsService.page(page, queryWrapper);
        long total = page1.getTotal();//记录数
        List<ParkingDetails> records = page1.getRecords();//集合
        return R.ok().data("total", total).data("records", records);
    }


    //10.根据停车位经纬度返回 adcode 行政编码 身份证前6位 省市区
    @ApiOperation("根据停车位经纬度返回 adcode")
    @PostMapping("parking_adcode/{lng}/{lat}")
    public R parking_adcode(
            @ApiParam(name = "lng", value = "停车位经度", required = true)
            @PathVariable String lng,
            @ApiParam(name = "lat", value = "停车位纬度", required = true)
            @PathVariable String lat
    ) {
        String[] res = new String[2];
        res = Gd.GPSToAddress(lng, lat);
        return R.ok().data("adcode", res[0]).data("address", res[1]);
    }

    //11.根据IP返回 经纬度 编码 地址
    @CachePut(value = "IPToGPS", key = "#IP")
    @ApiOperation("根据IP返回 经纬度 编码 地址")
    @PostMapping("IPToGPS/{IP}")
    public R IPToGPS(
            @ApiParam(name = "IP", value = "用户IP", required = true)
            @PathVariable String IP
    ) {
        String[] data = Gd.IPToGPS(IP);
        if (!data[0].equals(data[1])) {
            String[] res = new String[2];
            res = Gd.GPSToAddress(data[0], data[1]);
            return R.ok().data("adcode", res[0]).data("address", res[1]).data("longitude", data[0]).data("latitude", data[1]).data("IP", IP);
        } else {
            return R.error().message("根据IP查询地址失败！");
        }
    }

    //12.条件查询带分页查询 查询指定 adcode 与指定经纬度之间的距离
    @ApiOperation("条件查询带分页查询 查询指定 adcode 与指定经纬度之间的距离")
    @PostMapping("parkingDetailsFindadcode/{current}/{limit}/{adcode}/{lng}/{lat}/{sq}/{ds}/{currenttime}")
    public R parkingDetailsFindadcode(@PathVariable long current, @PathVariable long limit,
                                      @PathVariable String adcode, @PathVariable String lng,
                                      @PathVariable String lat, @PathVariable String sq,
                                      @PathVariable String ds, @PathVariable String currenttime) throws ParseException {
        //创建对象
        Page<ParkingDetails> page = new Page<>(current, limit);
        //构造条件
        QueryWrapper<ParkingDetails> queryWrapper = new QueryWrapper<>();
        //判断条件值是否为空
        if (!StringUtils.isEmpty(adcode + "")) {
            if (sq.equals("0")) {
                //构造条件
                queryWrapper.eq("adcode", adcode);
            } else {
                queryWrapper.ge("adcode", Integer.parseInt(adcode) / 100 * 100);        // >=
                queryWrapper.lt("adcode", (Integer.parseInt(adcode) / 100 + 1) * 100);    // <
            }
            //是否被预定
            queryWrapper.eq("appoint", "0");
            //停车位失效时间大于当前时间
            queryWrapper.ge("parking_expiretime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(currenttime));
        }
        //聚合查询停车位数量
        queryWrapper.select("owner_product_no,parking_details_note,price,distince,parking_expiretime,parking_details_longitude,parking_details_latitude,count(1) as count");
        queryWrapper.groupBy("owner_product_no","parking_details_note","price","distince","parking_expiretime","parking_details_longitude","parking_details_latitude");
        //调用方法使用条件查询
        IPage<ParkingDetails> page1 = parkingDetailsService.page(page, queryWrapper);
        long total = page1.getTotal();//记录数
        List<ParkingDetails> records = page1.getRecords();//集合
        //符合市区的所有停车位进行遍历，删除不满足距离的停车位
        Iterator iterator = records.iterator();
        while(iterator.hasNext()){
            ParkingDetails pd = (ParkingDetails) iterator.next();
            double distince = MapUtils.algorithm(Double.valueOf(lng), Double.valueOf(lat),
                    Double.valueOf(pd.getParkingDetailsLongitude()),
                    Double.valueOf(pd.getParkingDetailsLatitude()));
            if(!ds.equals("-1")){
                if(distince>(Integer.parseInt(ds)*1000)){
                    total--;
                    iterator.remove();
                }else {
                    pd.setDistince(distince + "");
                }
            }else {
                pd.setDistince(distince + "");
            }
        }
        return R.ok().data("total", total).data("records", records);
    }


    //13.条件查询带分页查询 查询指定地址的停车位编码 并设置停车位被预约
    @ApiOperation("条件查询带分页查询 查询指定地址的停车位编码 并设置停车位被预约")
    @PostMapping("parkingDetailsFindByAddress/{parkingDetailsNote}/{price}")
    public R parkingDetailsFindByAddress(@PathVariable String parkingDetailsNote,
                                         @PathVariable String price) {
        //创建对象
        Page<ParkingDetails> page = new Page<>(1, 1);
        //构造条件
        QueryWrapper<ParkingDetails> queryWrapper = new QueryWrapper<>();
        //column为字段名称，别传对象名，并未做驼峰转换
        queryWrapper.eq("parking_details_note", parkingDetailsNote);
        //价格
        queryWrapper.eq("price",price);
        //是否被预定
        queryWrapper.eq("appoint", "0");
        //排序
        queryWrapper.orderByDesc("parking_expiretime");

        //调用方法使用条件查询
        IPage<ParkingDetails> page1 = parkingDetailsService.page(page, queryWrapper);
        long total = page1.getTotal();//记录数
        List<ParkingDetails> records = page1.getRecords();//集合
        String parkingNo = null;
        for (ParkingDetails record : records) {
            parkingNo = record.getParkingNo();
            record.setAppoint(1);
        }
        return R.ok().data("total", total).data("records", records).data("parkingNo",parkingNo);
    }

    //14.根据停车位编号 set appoint 是否被预约
    @CacheEvict(value = "parking", allEntries = true)
    @ApiOperation("根据停车位编号 set appoint 是否被预约")
    @PostMapping("setAppointByParkingNo/{parkingNo}/{appoint}")
    public R setAppointByParkingNo(
            @ApiParam(name = "parkingNo", value = "停车位编号", required = true)
            @PathVariable String parkingNo,
            @ApiParam(name = "appoint", value = "是否被预约", required = true)
            @PathVariable long appoint
    ) {
        ParkingDetails parkingDetails = new ParkingDetails();
        parkingDetails.setAppoint(appoint);
        parkingDetails.setParkingNo(parkingNo);
        parkingDetailsService.updateById(parkingDetails);
        return R.ok();
    }

    //15.根据停车位编号 set appoint = 0 更新收益 earnings
    @CacheEvict(value = "parking", allEntries = true)
    @ApiOperation("根据停车位编号 set appoint = 0 更新收益 earnings")
    @PostMapping("setEarningsByParkingNo/{parkingNo}/{earnings}/{times}")
    public R setEarningsByParkingNo(
            @ApiParam(name = "parkingNo", value = "停车位编号", required = true)
            @PathVariable String parkingNo,
            @ApiParam(name = "earnings", value = "收益", required = true)
            @PathVariable String earnings,
            @ApiParam(name = "times", value = "时长", required = true)
            @PathVariable String times
    ) {
        //创建对象
        Page<ParkingDetails> page = new Page<>(1, 1);
        //构造条件
        QueryWrapper<ParkingDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parking_no",parkingNo);
        //调用方法使用条件查询
        IPage<ParkingDetails> page1 = parkingDetailsService.page(page, queryWrapper);
        String earnings1 = null;
        String times1 = null;
        String counts = null;
        List<ParkingDetails> records = page1.getRecords();//集合
        for (ParkingDetails record : records) {
            earnings1 = record.getEarnings();
            times1 = record.getParkingTimes();
            counts = record.getParkingCounts();
        }
        ParkingDetails parkingDetails = new ParkingDetails();
        parkingDetails.setEarnings((Integer.parseInt(earnings1) + Integer.parseInt(earnings))+"");
        parkingDetails.setParkingTimes((Integer.parseInt(times1) + Integer.parseInt(times))+"");
        parkingDetails.setParkingCounts((Integer.parseInt(counts) + 1 )+"");
        parkingDetails.setAppoint(0);
        parkingDetails.setParkingNo(parkingNo);
        parkingDetailsService.updateById(parkingDetails);
        return R.ok();
    }

}

