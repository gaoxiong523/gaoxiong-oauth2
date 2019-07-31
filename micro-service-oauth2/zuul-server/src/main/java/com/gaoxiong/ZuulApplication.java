package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author gaoxiong
 * @ClassName ZuulApplication
 * @Description TODO
 * @date 2019/7/23 17:20
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableOAuth2Sso
public class ZuulApplication {
    public static void main ( String[] args ) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
