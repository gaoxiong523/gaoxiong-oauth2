package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author gaoxiong
 * @ClassName EurekaServerApp
 * @Description TODO
 * @date 2019/7/12 10:43
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp {
    public static void main ( String[] args ) {
        SpringApplication.run(EurekaServerApp.class, args);
    }
}
