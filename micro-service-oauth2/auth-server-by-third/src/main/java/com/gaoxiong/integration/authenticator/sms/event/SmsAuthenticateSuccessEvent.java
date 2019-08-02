package com.gaoxiong.integration.authenticator.sms.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @author gaoxiong
 * @ClassName SmsAuthenticateSuccessEvent
 * @Description 短信认证成功事件
 * @date 2019/8/2 11:13
 */
@Slf4j
public class SmsAuthenticateSuccessEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SmsAuthenticateSuccessEvent ( Object source ) {
        super(source);
        log.info("短信认证成功,相关参数是:{}",source);
    }
}
