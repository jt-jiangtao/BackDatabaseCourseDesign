package com.jw.backdatabasecoursedesign.service;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 22:20
 */
public interface DepartmentInfoService {
    Object departmentCourse(String teacherId, Integer specialtyId, String year, String term, Boolean all, Integer deptId);

    Object departmentSpecialty(String teacherId, Integer deptId);

    Object departmentTeacher(String teacherId, Integer specialtyId, Integer deptId);

    Object departmentClass(String teacherId, Integer specialtyId, Integer deptId);
}
