package com.jw.backdatabasecoursedesign.controller.teacher;

import com.jw.backdatabasecoursedesign.service.TeacherStatisticService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 18:28
 */
@RestController
@CrossOrigin
@RequestMapping("/teacher")
public class TeacherStatisticController {
    @Autowired
    private TeacherStatisticService teacherStatisticService;

    @PostMapping("/fail")
    public Object courseFailInfo(@RequestParam(required = true, value = "token")String token,
                                 @RequestParam(required = false, value = "courseId", defaultValue = "-1")Integer courseId) {
        return teacherStatisticService.courseFailInfo(JWTUtils.getUserName(token), courseId);
    }

    @PostMapping("/extreme")
    public Object courseExtremeInfo(@RequestParam(required = true, value = "token")String token,
                                    @RequestParam(required = false, value = "courseId", defaultValue = "-1")Integer courseId){
        return teacherStatisticService.courseExtremeInfo(JWTUtils.getUserName(token), courseId);
    }

    @PostMapping("/unfinished")
    public Object putUnfinishedInfo(@RequestParam(required = true, value = "token")String token,
                                    @RequestParam(required = false, value = "courseId", defaultValue = "-1")Integer courseId){
        return teacherStatisticService.putUnfinishedInfo(JWTUtils.getUserName(token), courseId);
    }
}
