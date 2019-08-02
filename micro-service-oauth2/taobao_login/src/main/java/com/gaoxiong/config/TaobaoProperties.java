package com.gaoxiong.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gaoxiong
 * @ClassName TaobaoProperties
 * @Description TODO
 * @date 2019/7/31 16:01
 */
@ConfigurationProperties(prefix = "taobao")
@Data
public class TaobaoProperties {
    private String serverUrl;
    private String appKey;
    private String appSecret;
    private String sessionKey;

}
