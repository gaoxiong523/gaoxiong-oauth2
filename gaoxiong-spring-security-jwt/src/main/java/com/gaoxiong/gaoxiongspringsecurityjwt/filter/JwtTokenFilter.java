package com.gaoxiong.gaoxiongspringsecurityjwt.filter;

import com.gaoxiong.gaoxiongspringsecurityjwt.commom.Const;
import com.gaoxiong.gaoxiongspringsecurityjwt.service.UserService;
import com.gaoxiong.gaoxiongspringsecurityjwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author com.gaoxiong
 * @ClassName JwtTokenFilter
 * @Description token 过滤器, 用于每次外部对 接口请求时的token处理
 * @date 2019/7/10 10:55
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal ( HttpServletRequest request, 
                                      HttpServletResponse response, 
                                      FilterChain filterChain ) throws ServletException, IOException {
        String authHeader = request.getHeader(Const.HEADER_STRING);
        if (authHeader != null && authHeader.startsWith(Const.TOKEN_PREFIX)) {
            final String authToken = authHeader.substring(Const.TOKEN_PREFIX.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken,userDetails )) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response );
    }
}
