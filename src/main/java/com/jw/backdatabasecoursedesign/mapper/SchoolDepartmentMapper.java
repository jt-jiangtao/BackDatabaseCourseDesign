package com.jw.backdatabasecoursedesign.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 21:06
 */
public interface SchoolDepartmentMapper {

    @Select("SELECT id AS deptId, deptName FROM department WHERE status = 1")
    List<Map<String, Object>> selectDept();

}
