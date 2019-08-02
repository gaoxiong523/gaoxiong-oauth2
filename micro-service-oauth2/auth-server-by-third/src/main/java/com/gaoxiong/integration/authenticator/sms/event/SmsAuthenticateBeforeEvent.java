package com.gaoxiong.integration.authenticator.sms.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

/**
 * @author gaoxiong
 * @ClassName SmsAuthenticateBeforeEvent
 * @Description 短信认证之前的事件,可以监听 事件进行用户手机号自动注册
 * @date 2019/8/2 11:07
 */
@Slf4j
public class SmsAuthenticateBeforeEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SmsAuthenticateBeforeEvent ( Object source ) {
        super(source);
        //todo 进行手机号自动注册
        log.info("进行手机号自动注册,参数是{}",source);
    }
}
