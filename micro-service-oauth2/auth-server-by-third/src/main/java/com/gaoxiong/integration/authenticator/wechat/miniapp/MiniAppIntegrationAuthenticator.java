package com.gaoxiong.integration.authenticator.wechat.miniapp;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.gaoxiong.feignclient.SysUserClient;
import com.gaoxiong.integration.IntegrationAuthentication;
import com.gaoxiong.integration.authenticator.IntegrationAuthenticator;
import com.gaoxiong.model.SysUserAuthentication;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 小程序集成认证
 *
 * @author LIQIU
 * @date 2018-3-31
 **/
@Service
public class MiniAppIntegrationAuthenticator implements IntegrationAuthenticator {
    /**
     * 社会化登录类型 - 微信小程序
     */
    public final static String SOCIAL_TYPE_WECHAT_MINIAP = "wechatMA";
    @Autowired
    private SysUserClient sysUserClient;

    @Autowired
    private WxMaService wxMaService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUserAuthentication authenticate( IntegrationAuthentication integrationAuthentication) {
        WxMaJscode2SessionResult session = null;
        String password = integrationAuthentication.getAuthParameter("password");
        try {
            session = this.wxMaService.getUserService().getSessionInfo(password);
            WechatMiniAppToken wechatToken = new WechatMiniAppToken(session.getOpenid(), session.getUnionid(), session.getSessionKey());
            // 加密算法的初始向量
            wechatToken.setIv(integrationAuthentication.getAuthParameter("iv"));
            // 用户的加密数据
            wechatToken.setEncryptedData(integrationAuthentication.getAuthParameter("encryptedData"));
        } catch (WxErrorException e) {
            throw new InternalAuthenticationServiceException("获取微信小程序用户信息失败",e);
        }
        String openId = session.getOpenid();
        SysUserAuthentication sysUserAuthentication = sysUserClient.findUserBySocial(SOCIAL_TYPE_WECHAT_MINIAP, openId);
        if(sysUserAuthentication != null){
            sysUserAuthentication.setPassword(passwordEncoder.encode(password));
        }
        return sysUserAuthentication;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return SOCIAL_TYPE_WECHAT_MINIAP.equals(integrationAuthentication.getAuthType());
    }

    @Override
    public void complete(IntegrationAuthentication integrationAuthentication) {

    }
}
