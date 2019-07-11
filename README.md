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
