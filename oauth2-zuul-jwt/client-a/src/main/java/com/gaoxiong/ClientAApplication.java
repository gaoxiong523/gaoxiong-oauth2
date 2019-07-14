package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gaoxiong
 * @ClassName com.gaoxiong.ClientAApplication
 * @Description TODO
 * @date 2019/7/14 0014 下午 4:49
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ClientAApplication {
    public static void main ( String[] args ) {
        SpringApplication.run(ClientAApplication.class, args);
    }
}
