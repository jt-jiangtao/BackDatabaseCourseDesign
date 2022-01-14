package com.jw.backdatabasecoursedesign.interceptor;

import com.alibaba.fastjson.JSON;
import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 21:47
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("-------------------------------token verify-------------------------------");
        String uri = request.getRequestURI();
        String token = request.getParameter("token");
        if (token == null || token.equals("") || !JWTUtils.isVerify(token)) {
            PrintWriter writer = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            try {
                writer = response.getWriter();
                UnifyResponse unifyResponse = null;
                if (token == null || token.equals(""))
                    unifyResponse = new UnifyResponse(1002, "缺少参数token", request.getMethod() + " " + uri);
                else unifyResponse = new UnifyResponse(1001, "身份验证失败", request.getMethod() + " " + uri);
                String error = JSON.toJSONString(unifyResponse);
                writer.print(error);
                return false;

            } catch (IOException e) {
                System.out.println("token验证不通过");
                e.printStackTrace();
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
