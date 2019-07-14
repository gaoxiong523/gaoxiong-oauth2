package com.gaoxiong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author gaoxiong
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @date 2019/7/12 0012 下午 8:49
 */
@Configuration
@Order(10)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/client/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }
}
