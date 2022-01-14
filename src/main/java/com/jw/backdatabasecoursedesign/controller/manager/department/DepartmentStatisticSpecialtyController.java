package com.jw.backdatabasecoursedesign.controller.manager.department;

import com.jw.backdatabasecoursedesign.service.DepartmentStatisticSpecialtyService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import com.jw.backdatabasecoursedesign.utils.NowYearTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 20:49
 */
@RestController
@CrossOrigin
@RequestMapping("/dept")
public class DepartmentStatisticSpecialtyController {
    @Autowired
    private DepartmentStatisticSpecialtyService departmentStatisticSpecialtyService;

    @PostMapping("/distribute")
    public Object specialtyDistribute(@RequestParam(required = true, value = "token")String token,
                                      @RequestParam(required = false, value = "specialtyId", defaultValue = "-1")Integer specialtyId,
                                      @RequestParam(required = false, value = "year", defaultValue = "") String year,
                                      @RequestParam(required = false, value = "term", defaultValue = "") String term,
                                      @RequestParam(required = false, value = "all", defaultValue = "true")Boolean all,
                                      @RequestParam(required = true, value = "deptId")Integer deptId,
                                      @RequestParam(required = false, value = "grade", defaultValue = "-1")Integer grade,
                                      @RequestParam(required = false, value = "classId", defaultValue = "-1")Integer classId){
        // 想要不全选必须all设置为false
        if (year.equals("")) year = NowYearTerms.nowYear();
        if (term.equals("")) term = NowYearTerms.nowTerm();
        return departmentStatisticSpecialtyService.specialtyDistribute(JWTUtils.getUserName(token), specialtyId, year, term, all, deptId, grade, classId);
    }

    @PostMapping("/fail")
    public Object specialtyFail(@RequestParam(required = true, value = "token")String token,
                                @RequestParam(required = false, value = "specialtyId", defaultValue = "-1")Integer specialtyId,
                                @RequestParam(required = false, value = "year", defaultValue = "") String year,
                                @RequestParam(required = false, value = "term", defaultValue = "") String term,
                                @RequestParam(required = false, value = "all", defaultValue = "true")Boolean all,
                                @RequestParam(required = true, value = "deptId")Integer deptId,
                                @RequestParam(required = false, value = "grade", defaultValue = "-1")Integer grade,
                                @RequestParam(required = false, value = "classId", defaultValue = "-1")Integer classId){
        // 想要不全选必须all设置为false
        if (year.equals("")) year = NowYearTerms.nowYear();
        if (term.equals("")) term = NowYearTerms.nowTerm();
        return departmentStatisticSpecialtyService.specialtyFail(JWTUtils.getUserName(token), specialtyId, year, term, all, deptId, grade, classId);
    }

    @PostMapping("/extreme")
    public Object specialtyExtreme(@RequestParam(required = true, value = "token")String token,
                                   @RequestParam(required = false, value = "specialtyId",  defaultValue = "-1")Integer specialtyId,
                                   @RequestParam(required = false, value = "year", defaultValue = "") String year,
                                   @RequestParam(required = false, value = "term", defaultValue = "") String term,
                                   @RequestParam(required = false, value = "all", defaultValue = "true")Boolean all,
                                   @RequestParam(required = true, value = "deptId")Integer deptId,
                                   @RequestParam(required = false, value = "grade", defaultValue = "-1")Integer grade,
                                   @RequestParam(required = false, value = "classId", defaultValue = "-1")Integer classId){
        // 想要不全选必须all设置为false
        if (year.equals("")) year = NowYearTerms.nowYear();
        if (term.equals("")) term = NowYearTerms.nowTerm();
        return departmentStatisticSpecialtyService.specialtyExtreme(JWTUtils.getUserName(token), specialtyId, year, term, all, deptId, grade, classId);
    }
}
