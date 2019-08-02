package com.gaoxiong.feignclient;

import com.gaoxiong.model.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gaoxiong
 * @ClassName VerificationCodeClient
 * @Description TODO
 * @date 2019/8/2 10:44
 */
@Component
public interface VerificationCodeClient {
    /**
     * eureka service name
     */
    String SERVICE_ID = "common-service";
    /**
     * common api prefix
     */
    String API_PATH = "/api/v1";

    @RequestMapping(value = "/common/vcc/token", method = RequestMethod.GET)
    Result<String> getToken( @RequestParam("size") Integer size,
                             @RequestParam("expire") Long expire,
                             @RequestParam("type") String type,
                             @RequestParam("subject") String subject,
                             @RequestParam("sendSms") Boolean sendSms);

    @RequestMapping(value = "/common/vcc/validate", method = RequestMethod.GET)
    Result<Boolean> validate(@RequestParam("token") String token, @RequestParam("code") String code, @RequestParam("subject") String subject);

}
