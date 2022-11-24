package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hello")
@CrossOrigin
//@Service
//@Bean 自定义 Component// ComponentScan
public class helloWorld {

//    @RequestMapping(value = "world", method = RequestMethod.GET)
    @GetMapping("world")
    public String hello(){
        return "hello world";
    }
}
