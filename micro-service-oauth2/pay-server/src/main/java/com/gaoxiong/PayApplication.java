package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gaoxiong
 * @ClassName PayApplication
 * @Description TODO
 * @date 2019/7/24 9:15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PayApplication {
    public static void main ( String[] args ) {
        SpringApplication.run(PayApplication.class, args);
    }
}
