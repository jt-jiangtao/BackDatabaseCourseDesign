package com.jw.backdatabasecoursedesign.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 17:19
 */
public interface TeacherOrdinaryService {

    Object getOrdinaryScoreByTeacher(Integer courseId, Integer itemId, String teacherId);

    void downloadOrdinaryExcel(String id, Integer courseId, HttpServletResponse response) throws IOException;

}
