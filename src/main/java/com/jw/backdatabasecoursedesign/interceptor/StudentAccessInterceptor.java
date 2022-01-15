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
 * @Date: 2022/1/15 1:00
 */
// 对于学生接口， 学生之外的用户都要提供学号
// 校验该学生或用户是否有权限接触该学号对应学生端信息
// 权限范围控制
@Component
public class StudentAccessInterceptor  implements HandlerInterceptor {
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("-------------------------------student access verify-------------------------------");
        UserRight userRight = (UserRight) request.getAttribute("userRight");
        if (userRight == null) {
            serviceError(request, response);
            return false;
        }
        String studentId = request.getParameter("studentId");
        if (studentId == null || studentId.equals("")) {
            noStudentId(request, response);
            return false;
        }
        if (userRight.getAllRoles().contains("STUDENT")){
            if (! studentId.equals(JWTUtils.getUserName(request.getParameter("token")))){
                noAccessToStudent(studentId, request, response);
                return false;
            }
        }else if (userRight.getAllRoles().contains("TEACHER")){
            noAccessToStudent(studentId, request, response);
            return false;
        }else if (userRight.getAllRoles().contains("DEPT_MANAGER")){
            // 该管理员是否有对该学生成绩单访问权限
            if (!userRoleService.deptManagerHasAccessToStudent(studentId, userRight.getUserId())) {
                noAccessToStudent(studentId, request, response);
                return false;
            }
        }else if (userRight.getAllRoles().contains("SCHOOL_MANAGER")){
            return true;
        }else {
            userRoleError(request, response);
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void noStudentId(HttpServletRequest request, HttpServletResponse response) {
        fail(request, response, "studentId缺失", new UnifyResponse(2201, "studentId缺失"));
    }

    private void noAccessToStudent(String studentId, HttpServletRequest request, HttpServletResponse response) {
        fail(request, response, "没有查看该学生成绩单权限 " + studentId, new UnifyResponse(2202, "没有查看该学生成绩单权限 " + studentId ));
    }

    private void userRoleError(HttpServletRequest request, HttpServletResponse response) {
        fail(request, response, "用户角色错误", new UnifyResponse(1010, "用户角色错误"));
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
