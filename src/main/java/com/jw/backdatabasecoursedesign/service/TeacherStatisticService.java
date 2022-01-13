package com.jw.backdatabasecoursedesign.service;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 18:33
 */
public interface TeacherStatisticService {
    Object courseFailInfo(String id, Integer courseId);

    Object courseExtremeInfo(String id, Integer courseId);

    Object putUnfinishedInfo(String id, Integer courseId);
}
