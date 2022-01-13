package com.jw.backdatabasecoursedesign.controller.manager.department;

import com.jw.backdatabasecoursedesign.service.DepartmentStatisticClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 20:50
 */
@RestController
@CrossOrigin
@RequestMapping("/dept/class")
public class DepartmentStatisticClassController {
    @Autowired
    private DepartmentStatisticClassService departmentStatisticClassService;

    @PostMapping("/distribute")
    public Object classDistribute(){
        return null;
    }

    @PostMapping("/fail")
    public Object classFail(){
        return null;
    }

    @PostMapping("/extreme")
    public Object classExtreme(){
        return null;
    }
}
