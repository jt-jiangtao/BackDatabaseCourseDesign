package com.jw.backdatabasecoursedesign.controller.teacher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreItem;
import com.jw.backdatabasecoursedesign.service.TeacherNormalItemService;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 13:53
 */
@RestController
@CrossOrigin
@RequestMapping("/teacher/normalScore/item")
public class TeacherNormalItemController {
    @Autowired
    TeacherNormalItemService teacherNormalItemService;


    @PostMapping("/update")
    public Object normalItemUpdate(@RequestParam(required = true, value = "items") String items,
                                @RequestParam(required = true, value = "token") String token,
                                @RequestParam(required = true, value = "courseId") Integer courseId,
                                   @RequestParam(required = true, value = "teacherId")String teacherId) {
        List<OrdinaryScoreItem> itemList = JSON.parseArray(items, OrdinaryScoreItem.class);
        if (itemList.size() == 0) return new UnifyResponse(1401);
        for (int i = 0; i < itemList.size(); i++) {
            OrdinaryScoreItem ordinaryScoreItem = itemList.get(i);
            if (ordinaryScoreItem.getName() == null || ordinaryScoreItem.getProportion() == null || ordinaryScoreItem.getTime() == null) return new UnifyResponse(1402);

        }
        return teacherNormalItemService.updateNormalItem(teacherId, courseId, itemList);
    }

    @PostMapping("/get")
    public Object normalItemGet(@RequestParam(required = true, value = "token") String token,
                                @RequestParam(required = true, value = "courseId") Integer courseId,
                                @RequestParam(required = true, value = "teacherId")String teacherId){
        return teacherNormalItemService.getNormaItem(teacherId, courseId);
    }
}
