package com.jw.backdatabasecoursedesign.controller;

import com.jw.backdatabasecoursedesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 12:53
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object UserLogin(@RequestParam(required = true, value = "username")String username,
                            @RequestParam(required = true, value = "password") String password){
        return userService.login(username, password);
    }

}
