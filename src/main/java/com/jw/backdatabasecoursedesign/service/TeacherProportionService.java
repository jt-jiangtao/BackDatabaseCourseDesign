package com.jw.backdatabasecoursedesign.service;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 22:26
 */
public interface TeacherProportionService {

    Object getCourseProportion(Integer courseId, String id);

    Object updateCourseProportion(Integer courseId, String id, Double normalProportion, Double examProportion);

}
