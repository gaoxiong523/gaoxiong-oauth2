package com.gaoxiong.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoxiong
 * @ClassName TestController
 * @Description TODO
 * @date 2019/7/10 0010 下午 8:52
 */
@RestController
@RequestMapping("/permissions")
public class TestController {

    @GetMapping("/normal")
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    public String normal(){
        return "调用 normal  permissions 接口 成功";
    }
    @GetMapping("/medium")
    @PreAuthorize("hasAuthority('ROLE_MEDIUM')")
    public String medium(){
        return "调用 medium  permissions 接口 成功";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin(){
        return "调用 admin  permissions 接口 成功";
    }
}
