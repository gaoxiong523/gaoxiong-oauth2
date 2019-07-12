package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author gaoxiong
 * @ClassName ZuulServiceApp
 * @Description TODO
 * @date 2019/7/12 11:12
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulServiceApp {
    public static void main ( String[] args ) {
        SpringApplication.run(ZuulServiceApp.class, args);
    }
}
