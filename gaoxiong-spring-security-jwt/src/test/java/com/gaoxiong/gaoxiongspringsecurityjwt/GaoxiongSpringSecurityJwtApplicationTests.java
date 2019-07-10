package com.gaoxiong.gaoxiongspringsecurityjwt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GaoxiongSpringSecurityJwtApplicationTests {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Test
    public void contextLoads () {
        String encode = passwordEncoder().encode("123456");
        System.out.println("encode = " + encode);
    }

}
