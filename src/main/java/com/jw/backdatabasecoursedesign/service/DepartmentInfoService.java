package com.jw.backdatabasecoursedesign.service;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 22:20
 */
public interface DepartmentInfoService {
    Object departmentCourse(String teacherId, Integer specialtyId, String year, String term, Boolean all);

    Object departmentSpecialty(String teacherId);

    Object departmentTeacher(String teacherId, Integer specialtyId);

    Object departmentClass(String teacherId, Integer specialtyId);
}
