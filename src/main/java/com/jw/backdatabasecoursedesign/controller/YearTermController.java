package com.jw.backdatabasecoursedesign.controller;

import com.jw.backdatabasecoursedesign.service.YearTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 15:50
 */
@RestController
@CrossOrigin
public class YearTermController {

    @Autowired
    private YearTermService yearTermService;

    @PostMapping("/yearTerm")
    Object getYearTerm(){
        return yearTermService.getAllYearTerm();
    }

}
