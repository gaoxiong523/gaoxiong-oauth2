package com.gaoxiong.gaoxiongspringsecurityjwt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author gaoxiong
 * @ClassName PermissionsController
 * @Description TODO
 * @date 2019/7/10 11:50
 */
@RestController
@Slf4j
@RequestMapping("/permissions")
public class PermissionsController {

    /**
     * 测试普通权限
     * @return
     */
    @PreAuthorize("hasRole('ROLE_NORMAL')")
    @GetMapping("/test1")
    public String test1(){
        return "ROLE_NORMAL  /permissions/test1  接口调用成功!";
    }
    /**
     * 测试管理员权限
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/test2")
    public String test2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Object principal = authentication.getPrincipal();
        Object details = authentication.getDetails();
        Object credentials = authentication.getCredentials();
        return "ROLE_ADMIN  /permissions/test2  接口调用成功!";
    }


}
