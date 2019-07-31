package com.gaoxiong.config;

import com.gaoxiong.service.MyUserDetailsService;
import com.gaoxiong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author gaoxiong
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @date 2019/7/24 9:54
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure ( AuthenticationManagerBuilder auth ) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("gaoxiong")
                .password(passwordEncoder.encode("123456"))
                .roles("read");
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        //        http
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .requestMatchers()
//                .antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access")
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated();


//        http.requestMatchers()
//                .antMatchers("/login", "/oauth/authorize")
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll();

        //     http.csrf().disable();
        //不拦截 oauth 开放的资源
        http.requestMatchers()
                .anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll();
    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean () throws Exception {
        return super.authenticationManagerBean();
    }
}
