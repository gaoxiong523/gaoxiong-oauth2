package com.gaoxiong.gaoxiongspringsecurityjwt.commom;

/**
 * @author gaoxiong
 * @ClassName Const
 * @Description TODO
 * @date 2019/7/10 10:54
 */
public class Const {
    public static final long EXPIRATION_TIME = 432_000_000;     // 5天(以毫秒ms计)
    public static final String SECRET = "CodeSheepSecret";      // JWT密码
    public static final String TOKEN_PREFIX = "Bearer";         // Token前缀
    public static final String HEADER_STRING = "Authorization"; // 存放Token的Header Key
}
