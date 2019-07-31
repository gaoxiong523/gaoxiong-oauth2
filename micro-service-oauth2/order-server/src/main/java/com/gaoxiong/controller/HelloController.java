package com.gaoxiong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoxiong
 * @ClassName HelloController
 * @Description TODO
 * @date 2019/7/25 15:51
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello is permitall";
    }
}
