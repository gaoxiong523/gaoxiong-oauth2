package com.gaoxiong.integration;

/**
 * @author gaoxiong
 * @ClassName IntegrationAuthenticationContext
 * @Description 集成验证的上下文
 * @date 2019/8/2 9:32
 */
public class IntegrationAuthenticationContext {
    private static ThreadLocal<IntegrationAuthentication> holder = new ThreadLocal<>();

    public static void set ( IntegrationAuthentication integrationAuthentication ) {
        holder.set(integrationAuthentication);
    }

    public static IntegrationAuthentication get () {
        return holder.get();
    }

    public static void clear () {
        holder.remove();
    }
}
