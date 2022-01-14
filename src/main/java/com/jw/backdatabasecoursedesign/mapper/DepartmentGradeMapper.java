package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.grade.NormalStudent;
import org.apache.ibatis.annotations.Param;
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
            "          WHERE teacherInfoWithDepartment.deptId = #{deptId}\n" +
            "      ) <if test=\"specialtyId != -1\"> AND specialtyId = #{specialtyId} </if> <if test=\"all != true\"> AND year = #{year} AND term = #{term} </if>;</script>")
    List<Map<String, Object>> departmentCourse(String teacherId, Integer specialtyId, String year, String term, Boolean all, Integer deptId);

    @Select("SELECT *\n" +
            "FROM specialtyWithDepartment\n" +
            "WHERE deptId IN\n" +
            "      (\n" +
            "          SELECT teacherInfoWithDepartment.deptId\n" +
            "          FROM teacherInfoWithDepartment\n" +
            "          WHERE teacherInfoWithDepartment.deptId = #{deptId}\n" +
            "      );")
    List<Map<String, Object>> departmentSpecialty(String teacherId, Integer deptId);

    @Select("<script>SELECT *\n" +
            "FROM teacherInfoWithDepartment\n" +
            "WHERE deptId IN\n" +
            "      (\n" +
            "          SELECT teacherInfoWithDepartment.deptId\n" +
            "          FROM teacherInfoWithDepartment\n" +
            "          WHERE teacherInfoWithDepartment.deptId = #{deptId}\n" +
            "      ) <if test=\"specialtyId != -1\"> AND specialtyId = #{specialtyId} </if>;</script>")
    List<Map<String, Object>> departmentTeacher(String teacherId, Integer specialtyId, Integer deptId);

    @Select("<script>SELECT *\n" +
            "FROM classWithDepartment\n" +
            "WHERE deptId IN\n" +
            "      (\n" +
            "          SELECT teacherInfoWithDepartment.deptId\n" +
            "          FROM teacherInfoWithDepartment\n" +
            "          WHERE teacherInfoWithDepartment.deptId = #{deptId}\n" +
            "      ) <if test=\"specialtyId != -1\"> AND specialtyId = #{specialtyId} </if>;</script>")
    List<Map<String, Object>> departmentClass(String teacherId, Integer specialtyId, Integer deptId);

    List<NormalStudent> getFilterScore(@Param("specialtyId") Integer specialtyId,@Param("year") String year,@Param("term") String term,@Param("all") Boolean all,@Param("deptId") Integer deptId,@Param("nowGrade") Integer nowGrade,@Param("classId") Integer classId);
}
