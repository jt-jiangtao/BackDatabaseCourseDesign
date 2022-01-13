package com.jw.backdatabasecoursedesign.controller.manager.department;

import com.jw.backdatabasecoursedesign.service.DepartmentInfoService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import com.jw.backdatabasecoursedesign.utils.NowYearTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 20:47
 */
@RestController
@CrossOrigin
@RequestMapping("/dept")
public class DepartmentInfoController {
    @Autowired
    private DepartmentInfoService departmentInfoService;

    @PostMapping("/courses")
    public Object departmentCourse(@RequestParam(required = true, value = "token") String token,
                                   @RequestParam(required = false, value = "specialtyId", defaultValue = "-1") Integer specialtyId,
                                   @RequestParam(required = false, value = "year", defaultValue = "") String year,
                                   @RequestParam(required = false, value = "term", defaultValue = "") String term,
                                   @RequestParam(required = false, value = "all", defaultValue = "true")Boolean all) {
        // 想要不全选必须all设置为false
        if (year.equals("")) year = NowYearTerms.nowYear();
        if (term.equals("")) term = NowYearTerms.nowTerm();
        return departmentInfoService.departmentCourse(JWTUtils.getUserName(token), specialtyId, year, term, all);
    }

    @PostMapping("/specialty")
    public Object departmentSpecialty(@RequestParam(required = true, value = "token")String token){
        return departmentInfoService.departmentSpecialty(JWTUtils.getUserName(token));
    }

    @PostMapping("/teacher")
    public Object departmentTeacher(@RequestParam(required = true, value = "token")String token,
                                    @RequestParam(required = false, value = "specialtyId", defaultValue = "-1")Integer specialtyId){
        return departmentInfoService.departmentTeacher(JWTUtils.getUserName(token), specialtyId);
    }

    @PostMapping("/class")
    public Object departmentClass(@RequestParam(required = true, value = "token")String token,
                                  @RequestParam(required = false, value = "specialtyId", defaultValue = "-1")Integer specialtyId){
        return departmentInfoService.departmentClass(JWTUtils.getUserName(token), specialtyId);
    }
}
