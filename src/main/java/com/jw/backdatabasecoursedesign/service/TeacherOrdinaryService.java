package com.jw.backdatabasecoursedesign.service;

import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryStudentScore;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 17:19
 */
public interface TeacherOrdinaryService {

    Object getOrdinaryScoreByTeacher(Integer courseId, Integer itemId, String teacherId);

    void downloadOrdinaryExcel(String id, Integer courseId, HttpServletResponse response) throws IOException;

    Object uploadOrdinaryExcel(Integer courseId, String teacherId, MultipartFile file) throws IOException, InvalidFormatException;

    Object uploadOrdinarySQL(Integer courseId, String teacherId, MultipartFile file) throws IOException;

    Object addOrdinaryScore(String id, Integer courseId, List<OrdinaryStudentScore> items);

    Object deleteStudentOrdinaryScore(String id, List<Integer> studentOrdinaryScoreId);

    Object updateStudentOrdinaryScore(String id, Integer studentOrdinaryScoreId, Double newScore);
}
