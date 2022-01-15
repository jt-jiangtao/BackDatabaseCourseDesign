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

    @Select("SELECT COUNT(*) AS count\n" +
            "FROM teacherInfoWithDepartment\n" +
            "WHERE deptId = #{deptId}\n" +
            "  AND teacherId = #{id};")
    int deptManagerHasAccessToDept(String id, String deptId);

    @Select("SELECT COUNT(*)\n" +
            "FROM course\n" +
            "         JOIN yearTerm ON course.yearTermId = yearTerm.id\n" +
            "WHERE course.id = #{courseId}\n" +
            "  AND yearTerm.year = #{nowYear}\n" +
            "  AND yearTerm.term = #{nowTerm};")
    int courseYearTerm(String courseId, String nowYear, String nowTerm);

    @Select("SELECT COUNT(*)\n" +
            "FROM teacherInfoWithDepartment\n" +
            "WHERE teacherId = #{teacherId}\n" +
            "  AND deptId\n" +
            "    IN (\n" +
            "          SELECT deptId\n" +
            "          FROM teacherInfoWithDepartment\n" +
            "          WHERE teacherId = #{id}\n" +
            "      );")
    int sameDept(String teacherId, String id);
}
