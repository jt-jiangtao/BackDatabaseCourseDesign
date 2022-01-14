package com.jw.backdatabasecoursedesign.service;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 22:22
 */
public interface DepartmentStatisticSpecialtyService {


    Object specialtyDistribute(String id, Integer specialtyId, String year, String term, Boolean all, Integer deptId, Integer grade, Integer classId);

    Object specialtyFail(String id, Integer specialtyId, String year, String term, Boolean all, Integer deptId, Integer grade, Integer classId);

    Object specialtyExtreme(String id, Integer specialtyId, String year, String term, Boolean all, Integer deptId, Integer grade, Integer classId);
}
