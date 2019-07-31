package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gaoxiong
 * @ClassName OrderApplication
 * @Description TODO
 * @date 2019/7/24 9:10
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class OrderApplication {
    public static void main ( String[] args ) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
