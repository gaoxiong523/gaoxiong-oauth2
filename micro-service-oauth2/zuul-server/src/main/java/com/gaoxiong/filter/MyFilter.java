package com.gaoxiong.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

/**
 * @author gaoxiong
 * @ClassName MyFilter
 * @Description TODO
 * @date 2019/7/24 9:07
 */
@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType () {
        return "pre";
    }

    @Override
    public int filterOrder () {
        return 0;
    }

    @Override
    public boolean shouldFilter () {
        return true;
    }

    @Override
    public Object run () throws ZuulException {
        System.out.println(" 进入自定义过滤器" );

        return null;
    }
}
