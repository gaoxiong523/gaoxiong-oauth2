package com.gaoxiong.integration;

import lombok.Data;

import java.util.Map;

/**
 * @author gaoxiong
 * @ClassName IntegrationAuthentication
 * @Description
 * @date 2019/8/2 9:27
 */
@Data
public class IntegrationAuthentication {
    private String authType;
    private String username;
    private Map<String,String[]> authParameters;

    public String getAuthParameter(String parameter){
        String[] values = this.authParameters.get(parameter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }
}
