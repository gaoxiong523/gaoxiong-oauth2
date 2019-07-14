package com.gaoxiong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;

import java.util.concurrent.TimeUnit;

/**
 * @author gaoxiong
 * @ClassName ZuulAuthorizationServerConfig
 * @Description TODO
 * @date 2019/7/12 0012 下午 8:54
 */
@EnableAuthorizationServer
@Configuration
public class ZuulAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Override
    public void configure ( ClientDetailsServiceConfigurer clients ) throws Exception {
        clients.inMemory()
                .withClient("gaoxiong-zuul")
                .secret(new BCryptPasswordEncoder().encode("123456"))
                .redirectUris("http://localhost:7777/login")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("all")
                .autoApprove(false);
    }
    /**
     * jwt token 和oauth2 令牌之间的转换器
     * @return
     */
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
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory("106.12.84.126", 6379);
    }
    @Bean
    public RedisTokenStoreSerializationStrategy redisTokenStoreSerializationStrategy(){
        return new JacksonSerializationStrategy();
    }

    @Bean
    public RedisTokenStore redisTokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory());
        //设置序列化策略 ,
        redisTokenStore.setSerializationStrategy(redisTokenStoreSerializationStrategy());
        return redisTokenStore;
    }

    @Override
    public void configure ( AuthorizationServerEndpointsConfigurer endpoints ) throws Exception {
        endpoints.tokenStore(jwtTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter());
        DefaultTokenServices tokenServices = (DefaultTokenServices) endpoints.getDefaultAuthorizationServerTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1));
        endpoints.tokenServices(tokenServices);
    }

    @Override
    public void configure ( AuthorizationServerSecurityConfigurer security ) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");
    }
}
