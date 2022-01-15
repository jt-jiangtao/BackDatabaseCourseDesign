package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 13:03
 */
@Mapper
public interface UserMapper {

    User selectUserByUsername(String username);

    @Update("UPDATE user SET password = #{newPassword}\n" +
            "WHERE number = #{username} AND password = #{password}")
    int updatePassword(String username, String password, String newPassword);

    @Select("<script><choose><when test=\"role == 'STUDENT'\">\n" +
            "    SELECT studentId, classId, name, sex, classNumber, specialtyId, specialtyName, deptId, deptName, nowGrade FROM studentWithDepartment WHERE studentId = #{id}\n" +
            "</when><otherwise>\n" +
            "    SELECT teacherId, name, taskGroupId, sex, groupName, specialtyId, specialtyName, deptId, deptName FROM teacherInfoWithDepartment WHERE teacherId = #{id}\n" +
            "</otherwise></choose></script>")
    Map<String, Object> getUserInfo(String id, String role);
}
