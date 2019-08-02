package com.gaoxiong.integration.authenticator.sms;

import com.gaoxiong.feignclient.SysUserClient;
import com.gaoxiong.feignclient.VerificationCodeClient;
import com.gaoxiong.integration.IntegrationAuthentication;
import com.gaoxiong.integration.authenticator.AbstractPrepareableIntegrationAuthenticator;
import com.gaoxiong.integration.authenticator.sms.event.SmsAuthenticateBeforeEvent;
import com.gaoxiong.integration.authenticator.sms.event.SmsAuthenticateSuccessEvent;
import com.gaoxiong.model.Result;
import com.gaoxiong.model.SysUserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * @author gaoxiong
 * @ClassName SmsIntegrationAuthenticator
 * @Description 集成手机短信登陆验证
 * @date 2019/8/2 11:00
 */
@Component
public class SmsIntegrationAuthenticator extends AbstractPrepareableIntegrationAuthenticator implements ApplicationEventPublisherAware {
    @Autowired
    private SysUserClient sysUserClient;

    @Autowired
    private VerificationCodeClient verificationCodeClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ApplicationEventPublisher applicationEventPublisher;

    private final static String SMS_AUTH_TYPE = "sms";

    @Override
    public SysUserAuthentication authenticate ( IntegrationAuthentication integrationAuthentication ) {
       //获取密码, 实际是获取手机验证码
        String password = integrationAuthentication.getAuthParameter("password");
        //获取用户名,实际是获取手机号码
        String username = integrationAuthentication.getAuthParameter("username");
        //发布事件,可以进行监听自动注册用户,也可以发送到消息队列里
        this.applicationEventPublisher.publishEvent(new SmsAuthenticateBeforeEvent(integrationAuthentication));
        //通过手机号码查询用户
        SysUserAuthentication sysUserAuthentication = this.sysUserClient.findUserByPhoneNumber(username);
        if (sysUserAuthentication != null) {
            //将密码设为验证码
            sysUserAuthentication.setPassword(passwordEncoder.encode(password));
            //发布事件,可以监听事件进行消息通知
            this.applicationEventPublisher.publishEvent(new SmsAuthenticateSuccessEvent(integrationAuthentication));
        }
        return sysUserAuthentication;
    }

    @Override
    public void prepare ( IntegrationAuthentication integrationAuthentication ) {
        String smsToken = integrationAuthentication.getAuthParameter("sms_token");
        String smsCode = integrationAuthentication.getAuthParameter("password");
        String username = integrationAuthentication.getAuthParameter("username");
        Result<Boolean> result = verificationCodeClient.validate(smsToken, smsCode, username);
        if (!result.getData()) {
            throw new OAuth2Exception("验证码错误或已过期");
        }
    }

    @Override
    public boolean support ( IntegrationAuthentication integrationAuthentication ) {
        return SMS_AUTH_TYPE.equals(integrationAuthentication.getAuthType());
    }

    @Override
    public void complete ( IntegrationAuthentication integrationAuthentication ) {
        super.complete(integrationAuthentication);
    }

    @Override
    public void setApplicationEventPublisher ( ApplicationEventPublisher applicationEventPublisher ) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
