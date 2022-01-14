package com.jw.backdatabasecoursedesign.controller.teacher;

import com.jw.backdatabasecoursedesign.service.TeacherExaminationService;
import com.jw.backdatabasecoursedesign.service.TeacherOrdinaryService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 21:52
 */
@Controller
@CrossOrigin
public class ExcelDownloadController {
    @Autowired
    TeacherOrdinaryService teacherOrdinaryService;

    @Autowired
    TeacherExaminationService teacherExaminationService;

    @PostMapping("/teacher/normalScore/download/excel")
    public void downloadOrdinaryExcel(@RequestParam(required = false, value = "token")String token,
                                        @RequestParam(required = false, value = "courseId")Integer courseId,
                                        HttpServletResponse response) throws IOException {
        teacherOrdinaryService.downloadOrdinaryExcel(JWTUtils.getUserName(token), courseId, response);
    }

    @PostMapping("/teacher/examScore/download/excel")
    public void downloadExaminationExcel(@RequestParam(required = false, value = "token")String token,
                                         @RequestParam(required = false, value = "courseId")Integer courseId,
                                         HttpServletResponse response)  throws IOException{
        teacherExaminationService.downloadExaminationExcel(JWTUtils.getUserName(token), courseId, response);
    }

}
