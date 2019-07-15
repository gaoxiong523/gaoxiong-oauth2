package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gaoxiong
 * @ClassName Client1App
 * @Description TODO
 * @date 2019/7/10 16:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Client1App {
    public static void main ( String[] args ) {
        SpringApplication.run(Client1App.class, args);
    }
}
