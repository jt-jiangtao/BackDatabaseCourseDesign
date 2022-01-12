package com.jw.backdatabasecoursedesign.controller.teacher;

import com.alibaba.fastjson.JSON;
import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreItem;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryStudentScore;
import com.jw.backdatabasecoursedesign.service.TeacherOrdinaryService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 17:17
 */
@RestController
@Controller
@RequestMapping("/teacher/normalScore")
public class TeacherOrdinaryController {
    @Autowired
    TeacherOrdinaryService teacherOrdinaryService;

    @PostMapping("/get")
    public Object teacherGetOrdinaryScore(@RequestParam(required = true, value = "token") String token,
                                          @RequestParam(required = true, value = "courseId") Integer courseId,
                                          @RequestParam(required = false, value = "itemId", defaultValue = "-1") Integer itemId) {


        return teacherOrdinaryService.getOrdinaryScoreByTeacher(courseId, itemId, JWTUtils.getUserName(token));
    }

    @PostMapping("/upload/excel")
    public Object updateExcel(@RequestParam(required = true, value = "token") String token,
                              @RequestParam(required = true, value = "courseId") Integer courseId,
                              @RequestParam(required = true, value = "file") MultipartFile file) throws IOException, InvalidFormatException {
        if (! file.getOriginalFilename().endsWith(".xls")) return new UnifyResponse(1801);
        return teacherOrdinaryService.uploadOrdinaryExcel(courseId, JWTUtils.getUserName(token), file);
    }

    @PostMapping("/upload/sql")
    public Object updateSql(@RequestParam(required = true, value = "token") String token,
                            @RequestParam(required = true, value = "courseId") Integer courseId,
                            @RequestParam(required = true, value = "file") MultipartFile file) throws IOException {
        if (! file.getOriginalFilename().endsWith(".sql")) return new UnifyResponse(1807);
        return teacherOrdinaryService.uploadOrdinarySQL(courseId, JWTUtils.getUserName(token), file);
    }

    @PostMapping("/add")
    public Object addOrdinaryScore(@RequestParam(required = true, value = "items") String items,
                                   @RequestParam(required = true, value = "token") String token,
                                   @RequestParam(required = true, value = "courseId") Integer courseId){
        List<OrdinaryStudentScore> itemList = JSON.parseArray((String)items, OrdinaryStudentScore.class);
        return teacherOrdinaryService.addOrdinaryScore(JWTUtils.getUserName(token), courseId, itemList);
    }

    @PostMapping("/delete")
    public Object addOrdinaryScore(@RequestParam(required = true, value = "token") String token,
                                   @RequestParam(required = true, value = "studentOrdinaryScoreId") String studentOrdinaryScoreId){
        List<Integer> studentOrdinaryScoreIdList = JSON.parseArray(studentOrdinaryScoreId, Integer.class);
        return teacherOrdinaryService.deleteStudentOrdinaryScore(JWTUtils.getUserName(token), studentOrdinaryScoreIdList);
    }

    @PostMapping("/update")
    public Object updateOrdinaryScore(@RequestParam(required = true, value = "token") String token,
                                      @RequestParam(required = true, value = "studentOrdinaryScoreId") Integer studentOrdinaryScoreId,
                                      @RequestParam(required = true, value = "newScore") Double newScore){
        return teacherOrdinaryService.updateStudentOrdinaryScore(JWTUtils.getUserName(token), studentOrdinaryScoreId, newScore);
    }
}
