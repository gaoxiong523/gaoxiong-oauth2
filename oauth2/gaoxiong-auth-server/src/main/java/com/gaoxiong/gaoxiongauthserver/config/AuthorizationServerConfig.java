package com.gaoxiong.gaoxiongauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author gaoxiong
 * @ClassName AuthorizationServerConfig
 * @Description TODO
 * @date 2019/7/9 14:11
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

   @Bean
   public ClientDetailsService clientDetailsService(){
       return new JdbcClientDetailsService(dataSource);
   }

    /**
     * 这三个必须要重写的配置
     * 客户端配置项
     * 定义客户端详细信息服务的配置程序。可以初始化客户端详细信息，也可以只引用现有存储。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure ( ClientDetailsServiceConfigurer clients ) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setClientDetailsService(clientDetailsService());
        tokenServices.setSupportRefreshToken(true);
        return tokenServices;
    }

    /**
     * 授权服务器安全配置项
     * 定义令牌端点上的安全约束。
     * @param security
     * @throws Exception
     */
    @Override
    public void configure ( AuthorizationServerSecurityConfigurer security ) throws Exception {
        security.passwordEncoder(NoOpPasswordEncoder.getInstance())
                .checkTokenAccess("permitAll()");
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 定义授权和令牌端点以及令牌服务。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure ( AuthorizationServerEndpointsConfigurer endpoints ) throws Exception {
        super.configure(endpoints);
        endpoints.tokenStore(tokenStore())
                .tokenServices(authorizationServerTokenServices());
        endpoints.authenticationManager(authenticationManager);
    }
}
