package com.gaoxiong.integration.authenticator;

import com.gaoxiong.integration.IntegrationAuthentication;
import com.gaoxiong.model.SysUserAuthentication;

/**
 * @author gaoxiong
 * @ClassName IntegrationAuthenticator
 * @Description 集成的身份验证接口
 * @date 2019/8/2 9:22
 */
public interface IntegrationAuthenticator {

    /**
     * 处理继承认证
     * @param integrationAuthentication
     */
    SysUserAuthentication authenticate( IntegrationAuthentication integrationAuthentication);

    /**
     * 预处理
     * @param integrationAuthentication
     */
    void prepare(IntegrationAuthentication integrationAuthentication);

    /**
     * 判断是否支持认证类型
     * @param integrationAuthentication
     * @return
     */
    boolean support(IntegrationAuthentication integrationAuthentication);

    /**
     * 认证结束后执行
     * @param integrationAuthentication
     */
    void complete(IntegrationAuthentication integrationAuthentication);
}
