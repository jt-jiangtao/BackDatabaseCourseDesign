package com.jw.backdatabasecoursedesign.controller.teacher;

import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.service.TeacherOrdinaryService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public Object updateExcel(){
        return null;
    }
}
