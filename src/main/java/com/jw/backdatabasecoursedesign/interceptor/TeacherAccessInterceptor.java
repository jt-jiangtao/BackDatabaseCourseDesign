package com.jw.backdatabasecoursedesign.interceptor;

import com.alibaba.fastjson.JSON;
import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.right.UserRight;
import com.jw.backdatabasecoursedesign.service.UserRoleService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: jiangtao
 * @Date: 2022/1/15 16:18
 */
public class TeacherAccessInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("-------------------------------teacher access verify-------------------------------");
        UserRight userRight = (UserRight) request.getAttribute("userRight");
        if (userRight == null) {
            serviceError(request, response);
            return false;
        }
        String teacherId = request.getParameter("teacherId");
        if (teacherId == null || teacherId.equals("")) {
            normal(request, response, 2404, "teacherId缺失");
            return false;
        }

        if (userRight.getAllRoles().contains("SCHOOL_MANAGER")){
            return true;
        }else if(userRight.getAllRoles().contains("DEPT_MANAGER")){
            // 校验当前管理员是否和教师是同一个系部
            if (! userRoleService.sameDept(teacherId, userRight.getUserId())){
                normal(request, response,2405, "无查看该教师信息的权限");
                return false;
            }
        }else if (userRight.getAllRoles().contains("TEACHER")){
            if (! teacherId.equals(JWTUtils.getUserName(request.getParameter("token")))){
                normal(request, response,2405, "无查看该教师信息的权限");
                return false;
            }
        }else {
            userRoleError(request, response);
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void userRoleError(HttpServletRequest request, HttpServletResponse response) {
        fail(request, response, "用户角色错误", new UnifyResponse(1010, "用户角色错误"));
    }

    private void normal(HttpServletRequest request, HttpServletResponse response, int code, String msg) {
        fail(request, response, msg, new UnifyResponse(code, msg));
    }

    private void serviceError(HttpServletRequest request, HttpServletResponse response) {
        fail(request, response, "获取用户角色失败", new UnifyResponse(1011, "获取用户角色失败"));
    }

    private void fail(HttpServletRequest request, HttpServletResponse response, String msg, UnifyResponse unifyResponse) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            System.out.println(msg);
            String error = JSON.toJSONString(unifyResponse);
            writer.print(error);

        } catch (IOException e) {
            System.out.println(msg);
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
