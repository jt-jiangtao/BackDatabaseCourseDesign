package com.jw.backdatabasecoursedesign.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 22:27
 */
public interface DepartmentGradeMapper {

    @Select("<script>SELECT *\n" +
            "FROM courseWithTeacherAndDepartment\n" +
            "WHERE specialtyId IN\n" +
            "      (\n" +
            "          SELECT specialtyId\n" +
            "          FROM teacherInfoWithDepartment\n" +
            "          WHERE teacherInfoWithDepartment.teacherId = #{teacherId}\n" +
            "      ) <if test=\"specialtyId != -1\"> AND specialtyId = #{specialtyId} </if> <if test=\"all != true\"> AND year = #{year} AND term = #{term} </if>;</script>")
    List<Map<String, Object>> departmentCourse(String teacherId, Integer specialtyId, String year, String term, Boolean all);

    @Select("SELECT *\n" +
            "FROM specialtyWithDepartment\n" +
            "WHERE deptId IN\n" +
            "      (\n" +
            "          SELECT teacherInfoWithDepartment.deptId\n" +
            "          FROM teacherInfoWithDepartment\n" +
            "          WHERE teacherInfoWithDepartment.teacherId = #{teacherId}\n" +
            "      );")
    List<Map<String, Object>> departmentSpecialty(String teacherId);

    @Select("<script>SELECT *\n" +
            "FROM teacherInfoWithDepartment\n" +
            "WHERE deptId IN\n" +
            "      (\n" +
            "          SELECT teacherInfoWithDepartment.deptId\n" +
            "          FROM teacherInfoWithDepartment\n" +
            "          WHERE teacherInfoWithDepartment.teacherId = #{teacherId}\n" +
            "      ) <if test=\"specialtyId != -1\"> AND specialtyId = #{specialtyId} </if>;</script>")
    List<Map<String, Object>> departmentTeacher(String teacherId, Integer specialtyId);

    @Select("<script>SELECT *\n" +
            "FROM classWithDepartment\n" +
            "WHERE deptId IN\n" +
            "      (\n" +
            "          SELECT teacherInfoWithDepartment.deptId\n" +
            "          FROM teacherInfoWithDepartment\n" +
            "          WHERE teacherInfoWithDepartment.teacherId = #{teacherId}\n" +
            "      ) <if test=\"specialtyId != -1\"> AND specialtyId = #{specialtyId} </if>;</script>")
    List<Map<String, Object>> departmentClass(String teacherId, Integer specialtyId);
}
