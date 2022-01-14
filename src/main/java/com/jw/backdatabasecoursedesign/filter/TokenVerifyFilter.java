package com.jw.backdatabasecoursedesign.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 21:41
 */

//@WebFilter(urlPatterns = "/*")
public class TokenVerifyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("--------filter--------");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
