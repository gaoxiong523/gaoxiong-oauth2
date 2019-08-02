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
##oauth2 优雅集成短信验证码登录以及第三方登录
```text
link https://www.jb51.net/article/138531.htm
基于SpringCloud做微服务架构分布式系统时，OAuth2.0作为认证的业内标准，Spring Security OAuth2也提供了全套的解决方案来支持在Spring Cloud/Spring Boot环境下使用OAuth2.0，提供了开箱即用的组件。但是在开发过程中我们会发现由于Spring Security OAuth2的组件特别全面，这样就导致了扩展很不方便或者说是不太容易直指定扩展的方案，例如：
•图片验证码登录
•短信验证码登录
•微信小程序登录
•第三方系统登录
•CAS单点登录

，集成登录的思路如下：
•在进入流程之前先进行拦截，设置集成认证的类型，例如：短信验证码、图片验证码等信息。
•在拦截的通知进行预处理，预处理的场景有很多，比如验证短信验证码是否匹配、图片验证码是否匹配、是否是登录IP白名单等处理
•在UserDetailService.loadUserByUsername方法中，根据之前设置的集成认证类型去获取用户信息，例如：通过手机号码获取用户、通过微信小程序OPENID获取用户等等

接入这个流程之后，基本上就可以优雅集成第三方登录。

在这个类种主要完成2部分工作：1、根据参数获取当前的是认证类型，2、根据不同的认证类型调用不同的IntegrationAuthenticator.prepar进行预处理
```
```text
第二步,将拦截器放入到拦截链条中

```