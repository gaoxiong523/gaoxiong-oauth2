package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author com.gaoxiong
 * @ClassName com.gaoxiong.AuthServerApp
 * @Description TODO
 * @date 2019/7/10 16:47
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthServerApp {
    public static void main ( String[] args ) {
        SpringApplication.run(AuthServerApp.class, args);
    }
}
