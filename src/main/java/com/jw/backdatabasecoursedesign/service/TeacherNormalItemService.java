package com.jw.backdatabasecoursedesign.service;

import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreItem;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 14:26
 */
public interface TeacherNormalItemService {

    Object updateNormalItem(String id, Integer courseId, List<OrdinaryScoreItem> items);

    Object getNormaItem(String id, Integer courseId);

}
