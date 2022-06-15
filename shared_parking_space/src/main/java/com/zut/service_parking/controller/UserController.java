package com.zut.service_parking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zut.service_parking.entity.ParkingDetails;
import com.zut.service_parking.entity.User;
import com.zut.service_parking.mapper.UserMapper;
import com.zut.service_parking.service.UserService;
import com.zut.service_parking.utils.commonutils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.security.acl.LastOwnerException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-07
 */
@RestController
@RequestMapping("/service_parking/user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired//注入Service 默认按照类型
    //@Qualifier：根据名称进行注入
    private UserService userService;

    //1.查询指定手机号信息
    @Cacheable(value = "user",key ="'findByProductNo'+#productNo")
    @ApiOperation("查询指定手机号信息")
    @PostMapping("findByProductNo/{productNo}")
    public R findByProductNo(
            @PathVariable String productNo
    ){
        //创建对象
        Page<User> page = new Page<>(1,1);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        if(!StringUtils.isEmpty(productNo)){
            //构造条件
            userQueryWrapper.eq("product_no",productNo);
        }
        else {
            return R.ok();
        }
        //调用方法使用条件查询
        IPage<User> page1 = userService.page(page, userQueryWrapper);
        Long total = page1.getTotal();//记录数
        List<User> records = page1.getRecords();//集合
        String id="";
        String name="";
        String productNo1 ="";
        String sex ="";
        Long money = null;
        Date userCreatetime = null;
        String cardId="";
        String carId1="";
        String carId2="";
        String carId3="";
        for (User record : records) {
            id = record.getId();
            name = record.getName();
            productNo1 = record.getProductNo();
            sex = record.getSex();
            money = record.getMoney();
            userCreatetime = record.getUserCreatetime();
            cardId = record.getCardId();
            carId1 = record.getCarId1();
            carId2 = record.getCarId2();
            carId3 = record.getCarId3();
        }
        return R.ok().data("total",total).data("records",records)
                .data("id",id)
                .data("name",name)
                .data("productNo",productNo1)
                .data("sex",sex)
                .data("money",money)
                .data("userCreatetime",userCreatetime)
                .data("cardId",cardId)
                .data("carId1",carId1)
                .data("carId2",carId2)
                .data("carId3",carId3);
    }

    //2.根据个人ID修改个人信息
    @CacheEvict(value = "user",key ="'findByProductNo'+#productNo",allEntries = true)
    @ApiOperation("根据个人ID修改个人信息")
    @PostMapping("updateUserDetailsByID/{id}/{productNo}")
    public R updateUserDetailsByID(
            @ApiParam(name = "id",value = "个人ID",required = true)
            @PathVariable String id,
            @ApiParam(name = "productNo",value = "手机号",required = true)
            @PathVariable String productNo,
            @ApiParam(name = "UserDetails",value = "个人信息",required = true)
            @RequestBody(required = true) User user
    ){
        user.setId(id);
        userService.updateById(user);
        return R.ok();
    }

    //3.根据个人ID修改余额 扣费
    @CacheEvict(value = "user",key ="'findByProductNo'+#productNo",allEntries = true)
    @ApiOperation("根据个人ID修改余额 扣费")
    @PostMapping("updateUserMoneyByID/{id}/{productNo}/{money}")
    public R updateUserMoneyByID(
            @ApiParam(name = "id",value = "个人ID",required = true)
            @PathVariable String id,
            @ApiParam(name = "productNo",value = "手机号",required = true)
            @PathVariable String productNo,
            @ApiParam(name = "money",value = "消费",required = true)
            @PathVariable long money
            ){
        //创建对象
        Page<User> page = new Page<>(1, 1);
        //构造条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        //调用方法使用条件查询
        IPage<User> page1 = userService.page(page, queryWrapper);
        long money1 = 0;
        List<User> records = page1.getRecords();//集合
        for (User record : records) {
            money1 = record.getMoney();
        }
        User user = new User();
        user.setId(id);
        user.setMoney(money1-money);
        userService.updateById(user);
        return R.ok();
    }

    //4.根据个人手机号修改余额 加费
    @CacheEvict(value = "user",key ="'findByProductNo'+#productNo",allEntries = true)
    @ApiOperation("根据个人手机号修改余额 加费")
    @PostMapping("updateUserMoneyByProductNo/{productNo}/{money}")
    public R updateUserMoneyByProductNo(
            @ApiParam(name = "productNo",value = "手机号",required = true)
            @PathVariable String productNo,
            @ApiParam(name = "money",value = "充值",required = true)
            @PathVariable long money
    ){
        //创建对象
        Page<User> page = new Page<>(1, 1);
        //构造条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_no",productNo);
        //调用方法使用条件查询
        IPage<User> page1 = userService.page(page, queryWrapper);
        String id = "";
        long money1 = 0;
        List<User> records = page1.getRecords();//集合
        for (User record : records) {
            money1 = record.getMoney();
            id = record.getId();
        }
        User user = new User();
        user.setId(id);
        user.setMoney(money1+money);
        userService.updateById(user);
        return R.ok();
    }



}

