package com.gaoxiong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author gaoxiong
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @date 2019/7/24 17:23
 */
@Configuration
@Order(101)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated();
        http
                .csrf()
                .disable();
    }
}
