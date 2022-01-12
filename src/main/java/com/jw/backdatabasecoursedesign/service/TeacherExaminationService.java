package com.jw.backdatabasecoursedesign.service;

import com.jw.backdatabasecoursedesign.entity.grade.ExaminationStudentScore;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/12 17:23
 */
public interface TeacherExaminationService {

    Object getFinalScore(Integer courseId, String id);

    Object getExaminationScore(Integer courseId, String id);

    void downloadExaminationExcel(String id, Integer courseId, HttpServletResponse response) throws IOException;

    Object uploadExcel(String id, Integer courseId, MultipartFile file) throws IOException, InvalidFormatException;

    Object uploadSQL(String id, Integer courseId, MultipartFile file) throws IOException;

    Object checkWithCommit(List<ExaminationStudentScore> info, Integer courseId, String teacherId);

    Object deleteExaminationScore(String id, List<Integer> studentOrdinaryScoreId);

    Object updateStudentExaminationScore(String id, Integer studentExaminationScoreId, Double newScore);
}
