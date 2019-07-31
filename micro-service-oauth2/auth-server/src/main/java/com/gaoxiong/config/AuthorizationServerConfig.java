package com.gaoxiong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @author gaoxiong
 * @ClassName AuthorizationServerConfig
 * [/oauth/authorize]
 * [/oauth/token]
 * [/oauth/check_token]
 * [/oauth/confirm_access]
 * [/oauth/token_key]
 * [/oauth/error]
 * @date 2019/7/24 9:53
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private RedisConnectionFactory factory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure ( AuthorizationServerSecurityConfigurer security ) throws Exception {
        security.tokenKeyAccess("permitAll()")         //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
                .checkTokenAccess("isAuthenticated()") //url:/oauth/check_token allow check token
                .allowFormAuthenticationForClients();
    }

//    @Bean
//    public ClientDetailsService clientDetailsService(){
//        return new JdbcClientDetailsService(dataSource);
//    }

    /**
     * 配置客户端认证
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure ( ClientDetailsServiceConfigurer clients ) throws Exception {
        //指定数据库,初始化信息到数据库
//        clients.withClientDetails(clientDetailsService());
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder());
//        clients.inMemory()
//                .withClient("client_1")
//                .authorizedGrantTypes("client_credentials")
//                .scopes("all","read", "write")
//                .authorities("client_credentials")
//                .accessTokenValiditySeconds(7200)
//                .secret(passwordEncoder().encode("123456"))
//
//                .and().withClient("client_2")
//                .authorizedGrantTypes("password", "refresh_token","authorization_code")
//                .scopes("all","read", "write")
//                .accessTokenValiditySeconds(7200)
//                .refreshTokenValiditySeconds(10000)
//                .authorities("password")
//                .secret(passwordEncoder().encode("123456"))
//                .redirectUris("httpL//localhost:9003/login")
//
//                .and().withClient("client_3").authorities("authorization_code","refresh_token")
//                .secret(passwordEncoder().encode("123456"))
//                .authorizedGrantTypes("authorization_code")
//                .scopes("all","read", "write")
//                .accessTokenValiditySeconds(7200)
//                .refreshTokenValiditySeconds(10000)
//                .redirectUris("http://localhost:8080/callback","http://localhost:8080/signin")
//
//                .and().withClient("client_test")
//                .secret(passwordEncoder().encode("123456"))
//                .authorizedGrantTypes("all flow")
//                .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token","password", "implicit")
//                .redirectUris("http://localhost:8080/callback","http://localhost:8080/signin")
//                .scopes("all","read", "write")
//                .accessTokenValiditySeconds(7200)
//                .refreshTokenValiditySeconds(10000);
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new UserService();
//    }


    @Bean("jdbcTokenStore")
    public JdbcTokenStore getJdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }



    @Bean
    public RedisTokenStore redisTokenStore(){
        return new RedisTokenStore(factory);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("testSignKey");
        return converter;
    }

    @Override
    public void configure ( AuthorizationServerEndpointsConfigurer endpoints ) throws Exception {
//        endpoints.tokenStore(new RedisTokenStore(factory))
//                .authenticationManager(authenticationManager);
 /*       endpoints.authenticationManager(authenticationManager)
                //配置 JwtAccessToken 转换器
                //  .accessTokenConverter(jwtAccessTokenConverter())
                //refresh_token 需要 UserDetailsService is required
                //   .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(getJdbcTokenStore());
*/
//        endpoints.tokenEnhancer(jwtAccessTokenConverter()) //生成JWT类型的token
              endpoints  .tokenStore(redisTokenStore()).authenticationManager(authenticationManager);

    }
    /**
     * 使用非对称加密算法来对Token进行签名
     * @return
     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyPair keyPair = new KeyStoreKeyFactory(
//                new ClassPathResource("keystore.jks"), "foobar".toCharArray())
//                .getKeyPair("test");
//        converter.setKeyPair(keyPair);
//        return converter;
//    }
}
