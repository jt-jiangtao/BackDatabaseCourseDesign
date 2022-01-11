package com.jw.backdatabasecoursedesign.controller.teacher;

import com.jw.backdatabasecoursedesign.service.TeacherOrdinaryService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/teacher/normalScore/download/excel")
    public void downloadOrdinaryExcel(@RequestParam(required = false, value = "token")String token,
                                        @RequestParam(required = false, value = "courseId")Integer courseId,
                                        HttpServletResponse response) throws IOException {
        teacherOrdinaryService.downloadOrdinaryExcel(JWTUtils.getUserName(token), courseId, response);
    }
}
