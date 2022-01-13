package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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
}
