package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author gaoxiong
 * @ClassName com.gaoxiong.EurekaApplication
 * @Description TODO
 * @date 2019/7/23 16:55
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main ( String[] args ) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
