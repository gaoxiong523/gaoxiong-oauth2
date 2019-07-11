package com.gaoxiong;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author gaoxiong
 * @ClassName Test
 * @Description TODO
 * @date 2019/7/11 0011 下午 11:24
 */
public class Test {
    public static void main ( String[] args ) {
        PasswordEncoder p=  new BCryptPasswordEncoder();
        System.out.println(p.encode("123456"));
    }
}
