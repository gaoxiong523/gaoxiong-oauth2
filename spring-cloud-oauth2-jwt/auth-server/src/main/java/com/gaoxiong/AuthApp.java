package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gaoxiong
 * @ClassName ZuulApp
 * @Description TODO
 * @date 2019/7/15 10:15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthApp {
    public static void main ( String[] args ) {
        SpringApplication.run(AuthApp.class, args);
    }
}
