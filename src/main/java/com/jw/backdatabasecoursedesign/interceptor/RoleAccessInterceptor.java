package com.jw.backdatabasecoursedesign.interceptor;

import com.alibaba.fastjson.JSON;
import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.right.UserRight;
import com.jw.backdatabasecoursedesign.service.UserRoleService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 22:22
 */
// 接口权限控制
@Component
public class RoleAccessInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRoleService userRoleService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("-------------------------------role verify-------------------------------");
        String token = request.getParameter("token");
        UserRight userRight = userRoleService.getUserRight(JWTUtils.getUserName(token));
        if (userRight == null) {
            dealWithNotFindRole(request, response);
            return false;
        }
        request.setAttribute("userRight", userRight);
        String uri = request.getRequestURI();
        String needAccess = "";
        if (uri.startsWith("/api/school")){
            if (!userRight.getAllRoles().contains("SCHOOL_MANAGER")) {
                dealWithNotRoleAccess(request, response);
                return false;
            }
        }else if (uri.startsWith("/api/dept")){
            if (!userRight.getAllRoles().contains("SCHOOL_MANAGER") && !userRight.getAllRoles().contains("DEPT_MANAGER")) {
                dealWithNotRoleAccess(request, response);
                return false;
            }
        }else if (uri.startsWith("/api/teacher")){
            if (!userRight.getAllRoles().contains("SCHOOL_MANAGER") && !userRight.getAllRoles().contains("DEPT_MANAGER") && !userRight.getAllRoles().contains("TEACHER")) {
                dealWithNotRoleAccess(request, response);
                return false;
            }
        }else if (uri.startsWith("/api/student") && !userRight.getAllRoles().contains("DEPT_MANAGER") && !userRight.getAllRoles().contains("STUDENT")){
            if (!userRight.getAllRoles().contains("SCHOOL_MANAGER")) {
                dealWithNotRoleAccess(request, response);
                return false;
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void dealWithNotRoleAccess(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            System.out.println("没有对应功能的权限");
            UnifyResponse unifyResponse =  unifyResponse = new UnifyResponse(1009, "没有对应功能的权限", request.getMethod() + " " + request.getRequestURI());
            String error = JSON.toJSONString(unifyResponse);
            writer.print(error);

        } catch (IOException e) {
            System.out.println("没有对应功能的权限");
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    private void dealWithNotFindRole(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            System.out.println("查询角色失败");
            UnifyResponse unifyResponse =  unifyResponse = new UnifyResponse(1008, "查询用户角色失败", request.getMethod() + " " + request.getRequestURI());
            String error = JSON.toJSONString(unifyResponse);
            writer.print(error);

        } catch (IOException e) {
            System.out.println("查询角色失败");
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
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
