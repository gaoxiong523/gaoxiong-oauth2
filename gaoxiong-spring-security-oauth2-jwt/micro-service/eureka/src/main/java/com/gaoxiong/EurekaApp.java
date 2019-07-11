package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author gaoxiong
 * @ClassName EurekaApp
 * @Description TODO
 * @date 2019/7/11 0011 下午 9:50
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp {
    public static void main ( String[] args ) {
        SpringApplication.run(EurekaApp.class, args);
    }
}
