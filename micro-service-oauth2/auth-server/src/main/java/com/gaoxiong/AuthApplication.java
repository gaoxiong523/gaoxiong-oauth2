package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gaoxiong
 * @ClassName AuthApplication
 * @Description TODO
 * @date 2019/7/24 9:51
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class AuthApplication {
    public static void main ( String[] args ) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
