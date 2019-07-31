package com.gaoxiong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author gaoxiong
 * @ClassName OrderController
 * @Description TODO
 * @date 2019/7/24 9:12
 */
@RestController
public class OrderController {


    @GetMapping("/order")
    public String order( HttpServletRequest request ){
        System.out.println("--------Header-------");

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            System.out.println("request = " + request.getHeader(key));

        }
        System.out.println("--------Header-------");
        return "this is order controller";
    }

}
