package com.gaoxiong.gaoxiongspringsecurityjwt.controller;

import com.gaoxiong.gaoxiongspringsecurityjwt.pojo.User;
import com.gaoxiong.gaoxiongspringsecurityjwt.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoxiong
 * @ClassName JwtAuthController
 * @Description TODO
 * @date 2019/7/10 11:43
 */
@RestController
@Slf4j
@RequestMapping("/authentication")
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity createToken(String username,String password){
        String token = authService.login(username, password);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(  User toAdd ){
        return authService.register(toAdd);
    }
}
