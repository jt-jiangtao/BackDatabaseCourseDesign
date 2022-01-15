package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.right.UserRight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 23:18
 */
@Mapper
public interface UserRoleMapper {

    UserRight getUserRight(String id);

    @Select("SELECT COUNT(*) AS count\n" +
            "FROM studentWithDepartment\n" +
            "WHERE studentId = #{studentId}\n" +
            "  AND studentWithDepartment.deptId\n" +
            "    IN (\n" +
            "          SELECT teacherInfoWithDepartment.deptId FROM teacherInfoWithDepartment WHERE teacherId = #{teacherId}\n" +
            "      );")
    int DeptManagerHasAccessToStudent(String studentId, String teacherId);

}
