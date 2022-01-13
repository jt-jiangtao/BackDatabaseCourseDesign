package com.jw.backdatabasecoursedesign.controller.manager.school;

import com.jw.backdatabasecoursedesign.service.SchoolDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 20:51
 */
@RestController
@CrossOrigin
@RequestMapping("/school")
public class SchoolDepartmentController {
    @Autowired
    private SchoolDepartmentService schoolDepartmentService;

    @PostMapping("/dept")
    public Object getSchoolDepartment(@RequestParam(required = true, value = "token")String token){
        return schoolDepartmentService.getAllDepartment();
    }
}
