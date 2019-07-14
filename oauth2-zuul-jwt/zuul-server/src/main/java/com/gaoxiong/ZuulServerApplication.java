package com.gaoxiong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author gaoxiong
 * @ClassName ZuulServerApplication
 * @Description TODO
 * @date 2019/7/12 0012 下午 8:48
 */
@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
@EnableOAuth2Sso
public class ZuulServerApplication {
    public static void main ( String[] args ) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
