package com.jw.backdatabasecoursedesign.service;

import com.jw.backdatabasecoursedesign.entity.user.User;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 13:02
 */
public interface UserService {

    Object login(String username, String password);

}
