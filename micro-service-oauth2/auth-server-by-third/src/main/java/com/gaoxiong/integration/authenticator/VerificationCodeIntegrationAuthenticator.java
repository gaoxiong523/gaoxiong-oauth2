package com.gaoxiong.integration.authenticator;

import com.gaoxiong.feignclient.VerificationCodeClient;
import com.gaoxiong.integration.IntegrationAuthentication;
import com.gaoxiong.model.Result;
import com.gaoxiong.model.SysUserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * @author gaoxiong
 * @ClassName VerificationCodeIntegrationAuthenticator
 * @Description 集成验证码登陆
 * @date 2019/8/2 10:43
 */
@Component
public class VerificationCodeIntegrationAuthenticator extends AbstractPrepareableIntegrationAuthenticator {

    private final static String VERIFICATION_CODE_AUTH_TYPE = "vc";
    @Autowired
    private VerificationCodeClient verificationCodeClient;


    @Override
    public void prepare ( IntegrationAuthentication integrationAuthentication ) {
        String vcToken = integrationAuthentication.getAuthParameter("vc_token");
        String vcCode = integrationAuthentication.getAuthParameter("vc_code");
        Result<Boolean> result = verificationCodeClient.validate(vcToken, vcCode, null);
        if (!result.getData()) {
            throw new OAuth2Exception("验证码错误");
        }
    }

    @Override
    public boolean support ( IntegrationAuthentication integrationAuthentication ) {
        return VERIFICATION_CODE_AUTH_TYPE.equals(integrationAuthentication.getAuthType());
    }

    @Override
    public void complete ( IntegrationAuthentication integrationAuthentication ) {
        super.complete(integrationAuthentication);
    }
}
