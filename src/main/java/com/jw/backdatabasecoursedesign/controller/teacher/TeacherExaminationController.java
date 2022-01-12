package com.jw.backdatabasecoursedesign.controller.teacher;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.grade.ExaminationStudentScore;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryStudentScore;
import com.jw.backdatabasecoursedesign.service.TeacherExaminationService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/12 17:22
 */
@RestController
@RequestMapping("/teacher/examScore")
@CrossOrigin
public class TeacherExaminationController {
    @Autowired
    TeacherExaminationService teacherExaminationService;

    @PostMapping("/getFinal")
    public Object getFinalExaminationScore(@RequestParam(required = false, value = "token")String token,
                                           @RequestParam(required = false, value = "courseId")Integer courseId){
        return teacherExaminationService.getFinalScore(courseId, JWTUtils.getUserName(token));
    }

    @PostMapping("/get")
    public Object getExaminationScore(@RequestParam(required = false, value = "token")String token,
                                      @RequestParam(required = false, value = "courseId")Integer courseId){
        return teacherExaminationService.getExaminationScore(courseId, JWTUtils.getUserName(token));
    }

    @PostMapping("/upload/excel")
    public Object uploadExaminationExcel(@RequestParam(required = true, value = "token") String token,
                                         @RequestParam(required = true, value = "courseId") Integer courseId,
                                         @RequestParam(required = true, value = "file") MultipartFile file) throws IOException, InvalidFormatException {
        if (! file.getOriginalFilename().endsWith(".xls")) return new UnifyResponse(1901);
        return teacherExaminationService.uploadExcel(JWTUtils.getUserName(token), courseId, file);
    }

    @PostMapping("/upload/sql")
    public Object uploadExaminationSql(@RequestParam(required = true, value = "token") String token,
                                       @RequestParam(required = true, value = "courseId") Integer courseId,
                                       @RequestParam(required = true, value = "file") MultipartFile file) throws IOException {
        if (! file.getOriginalFilename().endsWith(".sql")) return new UnifyResponse(1902);
        return teacherExaminationService.uploadSQL(JWTUtils.getUserName(token), courseId, file);
    }

    @PostMapping("/add")
    public Object addExaminationScore(@RequestParam(required = true, value = "items") String items,
                                      @RequestParam(required = true, value = "token") String token,
                                      @RequestParam(required = true, value = "courseId") Integer courseId) {
        List<ExaminationStudentScore> itemList = JSON.parseArray((String) items, ExaminationStudentScore.class);
        return teacherExaminationService.checkWithCommit(itemList, courseId, JWTUtils.getUserName(token));
    }

    @PostMapping("/delete")
    public Object deleteExaminationScore(@RequestParam(required = true, value = "token") String token,
                                         @RequestParam(required = true, value = "studentExaminationScoreId") String studentExaminationScoreId) {
        List<Integer> studentExaminationScoreIdList = JSON.parseArray(studentExaminationScoreId, Integer.class);
        return teacherExaminationService.deleteExaminationScore(JWTUtils.getUserName(token), studentExaminationScoreIdList);
    }

    @PostMapping("/update")
    public Object updateOrdinaryScore(@RequestParam(required = true, value = "token") String token,
                                      @RequestParam(required = true, value = "studentExaminationScoreId") Integer studentExaminationScoreId,
                                      @RequestParam(required = true, value = "newScore") Double newScore){
        return teacherExaminationService.updateStudentExaminationScore(JWTUtils.getUserName(token), studentExaminationScoreId, newScore);
    }
}
