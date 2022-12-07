package com.controller;

import com.myUtils.SQLToUP;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/myUtils")
@CrossOrigin
public class myUtils {

    @PostMapping("/SQLToUpper")
    //sql关键字 src/main/resource/SQLToUP_SYMBOLS.txt  转换大写
    public String SQLToUpper(@RequestBody(required = false) String sql){
        if (sql==null) return "";
        return new SQLToUP().HandlerSQL(sql);
    }
}
