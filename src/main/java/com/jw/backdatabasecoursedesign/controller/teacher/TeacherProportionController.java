package com.jw.backdatabasecoursedesign.controller.teacher;

import com.jw.backdatabasecoursedesign.service.TeacherProportionService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 22:22
 */
@RestController
@CrossOrigin
@RequestMapping("/teacher/proportion")
public class TeacherProportionController {
    @Autowired
    TeacherProportionService teacherProportionService;

    @PostMapping("/get")
    public Object proportionGet(@RequestParam(required = true, value = "token")String token,
                                @RequestParam(required = true, value = "courseId")Integer courseId,
                                @RequestParam(required = true, value = "teacherId")String teacherId){
        return teacherProportionService.getCourseProportion(courseId, teacherId);
    }

    @PostMapping("/update")
    public Object proportionUpdate(@RequestParam(required = true, value = "token")String token,
                                   @RequestParam(required = true, value = "courseId")Integer courseId,
                                   @RequestParam(required = true, value = "normalProportion")Double normalProportion,
                                   @RequestParam(required = true, value = "examProportion")Double examProportion,
                                   @RequestParam(required = true, value = "teacherId")String teacherId){
        return teacherProportionService.updateCourseProportion(courseId, teacherId, normalProportion, examProportion);
    }
}
