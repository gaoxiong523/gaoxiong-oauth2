package com.gaoxiong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author gaoxiong
 * @ClassName SecurityConfig
 * @Description TODO
 * @date 2019/7/11 0011 下午 9:53
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http.csrf().disable()
                .authorizeRequests().anyRequest().permitAll();
    }
}
