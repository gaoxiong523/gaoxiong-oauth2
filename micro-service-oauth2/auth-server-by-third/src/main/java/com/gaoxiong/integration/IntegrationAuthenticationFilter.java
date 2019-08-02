package com.gaoxiong.integration;

import com.gaoxiong.integration.authenticator.IntegrationAuthenticator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;
import java.util.Collection;

/**
 * @author gaoxiong
 * @ClassName IntegrationAuthenticationFilter
 * @Description TODO
 * @date 2019/8/2 9:08
 */
@Component
public class IntegrationAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {

    private static final String AUTH_TYPE_PARAM_NAME = "auth_type";
    private static final String OAUTH_TOKEN_URL = "/oauth/token";

    private Collection<IntegrationAuthenticator> authenticators;

    private ApplicationContext applicationContext;

    @Override
    public void doFilter ( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {

    }

    @Override
    public void setApplicationContext ( ApplicationContext applicationContext ) throws BeansException {

    }
}
