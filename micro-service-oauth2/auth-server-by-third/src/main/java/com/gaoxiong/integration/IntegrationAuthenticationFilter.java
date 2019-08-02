package com.gaoxiong.integration;

import com.gaoxiong.integration.authenticator.IntegrationAuthenticator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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

    private RequestMatcher requestMatcher;

    public IntegrationAuthenticationFilter () {
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, HttpMethod.GET.toString()),
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, HttpMethod.POST.name())
        );
    }

    @Override
    public void doFilter ( ServletRequest servletRequest,
                           ServletResponse servletResponse,
                           FilterChain chain ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (requestMatcher.matches(request)) {
            //设置集成登录信息
            IntegrationAuthentication integrationAuthentication = new IntegrationAuthentication();
            integrationAuthentication.setAuthType(request.getAuthType());
            integrationAuthentication.setAuthParameters(request.getParameterMap());
            IntegrationAuthenticationContext.set(integrationAuthentication);

            try {
                //预处理
                this.prepare(integrationAuthentication);

                chain.doFilter(request, response);

                //后置处理
                this.complete(integrationAuthentication);
            } finally {
                IntegrationAuthenticationContext.clear();
            }
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void setApplicationContext ( ApplicationContext applicationContext ) throws BeansException {

    }

    private void prepare ( IntegrationAuthentication integrationAuthentication ) {
        //延迟加载认证器
        if (this.authenticators == null) {
            synchronized (this) {
                Map<String, IntegrationAuthenticator> map = applicationContext.getBeansOfType(IntegrationAuthenticator.class);
                if (map != null) {
                    this.authenticators =  map.values();
                }
            }
        }

        if (this.authenticators == null) {
            authenticators = new ArrayList<>();
        }
        for (IntegrationAuthenticator authenticator : authenticators) {
            if (authenticator.support(integrationAuthentication)) {
                authenticator.prepare(integrationAuthentication);
            }
        }
    }

    /**
     * 后置处理
     * @param integrationAuthentication
     */
    private void complete ( IntegrationAuthentication integrationAuthentication ) {
        for (IntegrationAuthenticator authenticator : authenticators) {
            if (authenticator.support(integrationAuthentication)) {
                authenticator.complete(integrationAuthentication);
            }
        }
    }

}
