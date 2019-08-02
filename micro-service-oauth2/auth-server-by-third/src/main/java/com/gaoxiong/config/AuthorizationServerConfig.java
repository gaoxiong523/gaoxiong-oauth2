package com.gaoxiong.config;

import com.gaoxiong.integration.IntegrationAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author gaoxiong
 * @ClassName AuthorizationServerConfig
 * @Description TODO
 * @date 2019/8/2 13:50
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private IntegrationAuthenticationFilter integrationAuthenticationFilter;

    @Override
    public void configure ( AuthorizationServerSecurityConfigurer security ) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                .addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);
    }

    @Override
    public void configure ( ClientDetailsServiceConfigurer clients ) throws Exception {
        super.configure(clients);
    }

    @Override
    public void configure ( AuthorizationServerEndpointsConfigurer endpoints ) throws Exception {
        super.configure(endpoints);
    }
}
