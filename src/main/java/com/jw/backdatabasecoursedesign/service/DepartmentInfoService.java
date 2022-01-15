package com.jw.backdatabasecoursedesign.service;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 22:20
 */
public interface DepartmentInfoService {
    Object departmentCourse(Integer specialtyId, String year, String term, Boolean all, Integer deptId);

    Object departmentSpecialty(Integer deptId);

    Object departmentTeacher(Integer specialtyId, Integer deptId);

    Object departmentClass(Integer specialtyId, Integer deptId);
}
