package com.gaoxiong.integration.authenticator;

import com.gaoxiong.integration.IntegrationAuthentication;
import com.gaoxiong.model.SysUserAuthentication;

/**
 * @author gaoxiong
 * @ClassName AbstractPrepareableIntegrationAuthenticator
 * @Description
 * @date 2019/8/2 9:35
 */
public abstract class AbstractPrepareableIntegrationAuthenticator implements IntegrationAuthenticator {
    @Override
    public SysUserAuthentication authenticate ( IntegrationAuthentication integrationAuthentication ) {

        return null;
    }

    @Override
    public void prepare ( IntegrationAuthentication integrationAuthentication ) {

    }

    @Override
    public boolean support ( IntegrationAuthentication integrationAuthentication ) {
        return false;
    }

    @Override
    public void complete ( IntegrationAuthentication integrationAuthentication ) {

    }
}
