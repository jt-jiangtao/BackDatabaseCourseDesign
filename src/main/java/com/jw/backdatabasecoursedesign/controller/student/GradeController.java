package com.jw.backdatabasecoursedesign.controller.student;

import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.service.GradeService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import com.jw.backdatabasecoursedesign.utils.NowYearTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 16:54
 */
@RestController
@CrossOrigin
@RequestMapping("/student")
public class GradeController {
    @Autowired
    GradeService gradeService;

    @PostMapping("/score")
    public Object studentScore(@RequestParam(required = true, value = "token") String token,
                               @RequestParam(required = false, value = "type", defaultValue = "ORDINARY") String type,
                               @RequestParam(required = false, value = "all", defaultValue = "true") boolean all,
                               @RequestParam(required = false, value = "year", defaultValue = "") String year,
                               @RequestParam(required = false, value = "term", defaultValue = "") String term,
                               @RequestParam(required = true, value = "studentId")String studentId) {
        if (year.equals("")) year = NowYearTerms.nowYear();
        if (term.equals("")) term = NowYearTerms.nowTerm();
        if (!type.equals("ORDINARY") && !type.equals("COMPOSITION")) return new UnifyResponse(1202);
        if (type.equals("ORDINARY")) return gradeService.studentNormalScore(studentId, year, term, all);
        return gradeService.studentAllScore(studentId, year, term, all);
    }

    @PostMapping("/gpa")
    public Object studentGPA(@RequestParam(required = true, value = "token") String token,
                             @RequestParam(required = false, value = "all", defaultValue = "true") boolean all,
                             @RequestParam(required = false, value = "year", defaultValue = "") String year,
                             @RequestParam(required = false, value = "term", defaultValue = "") String term,
                             @RequestParam(required = true, value = "studentId")String studentId) {
        if (year.equals("")) year = NowYearTerms.nowYear();
        if (term.equals("")) term = NowYearTerms.nowTerm();
        return gradeService.studentGPA(studentId, year, term, all);
    }
}
