package com.qingnian.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
任涛 -----专用
*/
@RestController
public class WebController {

    @GetMapping("/web/interceptor")
    public String getInfo(){
         return "interceptor........./////";
    }

    @GetMapping("/web/interceptor1")
    public String getInfo1(){
        return "interceptor........./////";
    }

    public static void main(String[] args) {
        System.out.print("master have data");
        System.out.print("dev_rt private");

    }


}
