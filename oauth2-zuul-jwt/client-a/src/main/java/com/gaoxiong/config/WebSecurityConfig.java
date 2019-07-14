package com.gaoxiong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author gaoxiong
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @date 2019/7/14 0014 下午 4:57
 */
@Configuration
@EnableResourceServer
public class WebSecurityConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure ( HttpSecurity http ) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**")
                .authenticated()
                .antMatchers(HttpMethod.GET, "/test")
                .hasAuthority("ROLR_NORMAL");
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("testKey");
        return converter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Override
    public void configure ( ResourceServerSecurityConfigurer resources ) throws Exception {
        resources.tokenStore(jwtTokenStore());
    }
}
