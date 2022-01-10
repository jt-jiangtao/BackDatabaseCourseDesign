package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 13:03
 */
@Mapper
public interface UserMapper {

    User selectUserByUsername(String username);

}
