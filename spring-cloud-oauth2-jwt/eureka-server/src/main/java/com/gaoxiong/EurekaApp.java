package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author gaoxiong
 * @ClassName ZuulApp
 * @Description TODO
 * @date 2019/7/15 10:15
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp {
    public static void main ( String[] args ) {
        SpringApplication.run(EurekaApp.class, args);
    }
}
