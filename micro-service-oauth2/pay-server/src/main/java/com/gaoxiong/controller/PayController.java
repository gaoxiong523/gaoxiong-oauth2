package com.gaoxiong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoxiong
 * @ClassName OrderController
 * @Description TODO
 * @date 2019/7/24 9:12
 */
@RestController
public class PayController {


    @GetMapping("/pay")
    public String pay(){
        return "this is pay controller";
    }

}
