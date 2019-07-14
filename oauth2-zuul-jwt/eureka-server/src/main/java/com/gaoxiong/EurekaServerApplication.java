package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author gaoxiong
 * @ClassName EurekaServerApplication
 * @Description TODO
 * @date 2019/7/14 0014 下午 8:55
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main ( String[] args ) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
