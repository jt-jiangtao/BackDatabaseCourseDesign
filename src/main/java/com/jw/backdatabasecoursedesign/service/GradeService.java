package com.jw.backdatabasecoursedesign.service;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 16:36
 */
public interface GradeService {

    Object studentNormalScore(String id, String year, String term, boolean all);

    Object studentAllScore(String id, String year, String term, boolean all);

    Object studentGPA(String id, String year, String term, boolean all);
}
