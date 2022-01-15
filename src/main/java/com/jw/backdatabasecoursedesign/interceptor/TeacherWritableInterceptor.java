package com.jw.backdatabasecoursedesign.interceptor;

import com.alibaba.fastjson.JSON;
import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.right.UserRight;
import com.jw.backdatabasecoursedesign.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: jiangtao
 * @Date: 2022/1/15 16:20
 */
public class TeacherWritableInterceptor implements HandlerInterceptor {
    @Autowired
    UserRoleService userRoleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("-------------------------------grade input time access verify-------------------------------");
        UserRight userRight = (UserRight) request.getAttribute("userRight");
        if (userRight == null) {
            serviceError(request, response);
            return false;
        }
        // 除了教师之外都不能调用
        if ( !userRight.getAllRoles().contains("TEACHER")){
            noAccessExceptTeacher(request, response);
        }
        // 有课程号，只能在指定的时间调用
        // courseId
        String courseId = request.getParameter("courseId");
        if (courseId == null || courseId.equals("")) {
            normal(request, response, 2402, "courseId参数缺失");
            return false;
        }
        if (! userRoleService.courseCanInput(courseId)){
            normal(request, response, 2403, "当前课程不为本学期课程，无法变动");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void normal(HttpServletRequest request, HttpServletResponse response, int code, String msg) {
        fail(request, response, msg, new UnifyResponse(code, msg));
    }

    private void noAccessExceptTeacher(HttpServletRequest request, HttpServletResponse response) {
        fail(request, response, "无权限调用教师端写接口", new UnifyResponse(2401, "无权限调用教师端写接口"));
    }

    private void serviceError(HttpServletRequest request, HttpServletResponse response) {
        fail(request, response, "获取用户角色失败", new UnifyResponse(1011, "获取用户角色失败"));
    }

    private void userRoleError(HttpServletRequest request, HttpServletResponse response) {
        fail(request, response, "用户角色错误", new UnifyResponse(1010, "用户角色错误"));
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
