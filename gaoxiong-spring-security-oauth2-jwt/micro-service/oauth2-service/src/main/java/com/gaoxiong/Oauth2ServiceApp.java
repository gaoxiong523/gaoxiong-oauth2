package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gaoxiong
 * @ClassName EurekaApp
 * @Description TODO
 * @date 2019/7/11 0011 下午 9:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Oauth2ServiceApp {
    public static void main ( String[] args ) {
        SpringApplication.run(Oauth2ServiceApp.class, args);
    }
}
