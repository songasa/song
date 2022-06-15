package com.zut.service_parking.controller;

import com.zut.service_parking.utils.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service_parking/admin")
@CrossOrigin //解决跨域问题
public class ParkingLoginController {

    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg9.doubanio.com%2Fview%2Fgroup_topic%2Fl%2Fpublic%2Fp454445646.jpg");
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
