package com.gaoxiong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author gaoxiong
 * @ClassName TestController
 * @Description TODO
 * @date 2019/7/14 0014 下午 4:52
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test ( HttpServletRequest request) {
        System.out.println("-----------------header---------");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            System.out.println(key + ": " + request.getHeader(key));
        }
        System.out.println("-----------------header---------");
        return "helloooooooooooooooooooooo";
    }
}
