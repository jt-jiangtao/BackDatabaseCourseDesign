package com.jw.backdatabasecoursedesign.controller.manager.department;

import com.jw.backdatabasecoursedesign.service.DepartmentStatisticSpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 20:49
 */
@RestController
@CrossOrigin
@RequestMapping("/dept/pro")
public class DepartmentStatisticSpecialtyController {
    @Autowired
    private DepartmentStatisticSpecialtyService departmentStatisticSpecialtyService;

    @PostMapping("/distribute")
    public Object specialtyDistribute(){
        return null;
    }

    @PostMapping("/fail")
    public Object specialtyFail(){
        return null;
    }

    @PostMapping("/extreme")
    public Object specialtyExtreme(){
        return null;
    }
}
