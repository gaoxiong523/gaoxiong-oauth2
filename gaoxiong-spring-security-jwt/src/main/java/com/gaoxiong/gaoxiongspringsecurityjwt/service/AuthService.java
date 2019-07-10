package com.gaoxiong.gaoxiongspringsecurityjwt.service;

import com.gaoxiong.gaoxiongspringsecurityjwt.pojo.User;
import org.springframework.http.ResponseEntity;

/**
 * @author gaoxiong
 * @ClassName AuthService
 * @Description TODO
 * @date 2019/7/10 11:19
 */
public interface AuthService {
    ResponseEntity<User> register ( User toAdd );
    String login(String username,String password);
}
