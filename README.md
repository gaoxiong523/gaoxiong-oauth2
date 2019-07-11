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