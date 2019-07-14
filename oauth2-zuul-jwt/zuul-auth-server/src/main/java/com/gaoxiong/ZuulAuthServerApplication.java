package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gaoxiong
 * @ClassName ZuulAuthServerApplication
 * @Description TODO
 * @date 2019/7/12 0012 下午 8:52
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZuulAuthServerApplication {

    public static void main ( String[] args ) {
        SpringApplication.run(ZuulAuthServerApplication.class, args);
    }
}
