package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
@CrossOrigin
@Service
//@Bean 自定义 Component// ComponentScan
public class helloWorld {

    @RequestMapping(value = "world", method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }
}
