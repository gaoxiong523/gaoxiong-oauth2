package com.gaoxiong.integration.authenticator;

import com.gaoxiong.feignclient.SysUserClient;
import com.gaoxiong.integration.IntegrationAuthentication;
import com.gaoxiong.model.SysUserAuthentication;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author gaoxiong
 * @ClassName UsernamePasswordAuthenticator
 * @Description 默认登陆处理
 * @date 2019/8/2 10:26
 */
@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPrepareableIntegrationAuthenticator {
    @Autowired
    private SysUserClient sysUserClient;


    @Override
    public SysUserAuthentication authenticate ( IntegrationAuthentication integrationAuthentication ) {
        return sysUserClient.findUserByUsername(integrationAuthentication.getUsername());
    }

    @Override
    public void prepare ( IntegrationAuthentication integrationAuthentication ) {
        super.prepare(integrationAuthentication);
    }

    @Override
    public boolean support ( IntegrationAuthentication integrationAuthentication ) {
        return StringUtils.isEmpty(integrationAuthentication.getAuthType());
    }

    @Override
    public void complete ( IntegrationAuthentication integrationAuthentication ) {
        super.complete(integrationAuthentication);
    }
}
