package com.jw.backdatabasecoursedesign.service;

import com.jw.backdatabasecoursedesign.entity.right.UserRight;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 23:15
 */
public interface UserRoleService {

    UserRight getUserRight(String id);

    Boolean deptManagerHasAccessToStudent(String studentId, String teacherId);

    Boolean deptManagerHasAccessToDept(String id, String deptId);

    boolean courseCanInput(String courseId);

    Boolean sameDept(String teacherId, String id);
}
