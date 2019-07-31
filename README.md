# com.gaoxiong-oauth2
强制push 解决Push rejected: Push to origin/master was rejected 错误
git push -u origin master -f
```text
spring security oauth2 中的 endpoint（聊聊spring security oauth2的几个endpoint的认证）

/oauth/authorize(授权端，授权码模式使用)
/oauth/token(令牌端，获取 token)
/oauth/check_token(资源服务器用来校验token)
/oauth/confirm_access(用户发送确认授权)
/oauth/error(认证失败)
/oauth/token_key(如果使用JWT，可以获的公钥用于 token 的验签)
```
客户端 client凭据 和 用户 user的凭据可以用数据库进行统一管理

认证 token也可以用数据库或缓存进行统一管理

授权认证中心的统一登录页面可以自定义成需要的样子

认证中心的授权页也可以自定义，甚至可以去掉

包括一些异常提示也可以自定义

```text
通过feign调用, 添加 token 头信息

@Configuration
public class FeignOauth2RequestInterceptor implements RequestInterceptor {

    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String BEARER_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
        }

    }
}

```

micro-service-oauth2 可正确达到我们的目的.
```java
   endpoints.tokenEnhancer(jwtAccessTokenConverter()) //生成JWT类型的token

```