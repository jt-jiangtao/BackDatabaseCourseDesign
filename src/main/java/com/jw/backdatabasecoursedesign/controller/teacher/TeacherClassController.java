package com.jw.backdatabasecoursedesign.controller.teacher;

import com.jw.backdatabasecoursedesign.service.TeacherClassService;
import com.jw.backdatabasecoursedesign.utils.NowYearTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 21:49
 */
@RestController
@CrossOrigin
@RequestMapping("/teacher")
public class TeacherClassController {
    @Autowired
    TeacherClassService teacherClassService;

    @PostMapping("/course")
    public Object getTeacherCourse(@RequestParam(required = true, value = "id") String id,
                                   @RequestParam(required = false, value = "year", defaultValue = "") String year,
                                   @RequestParam(required = false, value = "term", defaultValue = "") String term) {
        if (year.equals("")) year = NowYearTerms.nowYear();
        if (term.equals("")) term = NowYearTerms.nowTerm();
        return teacherClassService.getTeacherClass(id, year, term);
    }

}
