package com.jw.backdatabasecoursedesign.controller;

import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.service.UserService;
import com.jw.backdatabasecoursedesign.utils.PasswordVerifyUtils;
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

    @PostMapping("/bank/update")
    public Object updateBank(@RequestParam(required = true, value = "username") String username,
                             @RequestParam(required = true, value = "password") String password,
                             @RequestParam(required = true, value = "newPassword") String newPassword) {
        if (! PasswordVerifyUtils.isLetterDigit(newPassword)) return new UnifyResponse(1103);
        if (password.equals(newPassword)) return new UnifyResponse(1105);
        return userService.updatePassword(username, password, newPassword);
    }
}
