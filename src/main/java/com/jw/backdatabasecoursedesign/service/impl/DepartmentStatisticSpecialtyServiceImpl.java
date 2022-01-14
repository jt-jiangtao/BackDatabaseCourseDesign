package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.grade.NormalCourse;
import com.jw.backdatabasecoursedesign.entity.grade.NormalStudent;
import com.jw.backdatabasecoursedesign.mapper.DepartmentGradeMapper;
import com.jw.backdatabasecoursedesign.service.DepartmentStatisticSpecialtyService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 22:22
 */
@Service
public class DepartmentStatisticSpecialtyServiceImpl implements DepartmentStatisticSpecialtyService {
    SqlSessionFactory sqlSessionFactory = null;

    public DepartmentStatisticSpecialtyServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public Object specialtyDistribute(String id, Integer specialtyId, String year, String term, Boolean all, Integer deptId, Integer grade, Integer classId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        List<NormalStudent> students = departmentGradeMapper.getFilterScore(specialtyId, year, term, all, deptId, grade, classId);
        for (int i = 0; i < students.size(); i++) {
            NormalStudent normalStudent = students.get(i);
            List<NormalCourse> courses = normalStudent.getCourses();
            double scoreSum = 0;
            double scoreCalculated = 0;
            for (int j = 0; j < courses.size(); j++) {
                NormalCourse course = courses.get(j);
                scoreCalculated += course.getCourseScore() * (course.getScore() / 10 - 5);
                scoreSum += course.getCourseScore();
            }
            if (scoreSum != 0) {
                normalStudent.setScorePoint(scoreCalculated / scoreSum);
            }
        }
        Collections.sort(students, (o1, o2) -> (int) (o1.getScorePoint() - o2.getScorePoint()));
        return students;
    }

    @Override
    public Object specialtyFail(String id, Integer specialtyId, String year, String term, Boolean all, Integer deptId, Integer grade, Integer classId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        List<NormalStudent> students = departmentGradeMapper.getFilterScore(specialtyId, year, term, all, deptId, grade, classId);
        for (int i = students.size() - 1; i >= 0; i--) {
            NormalStudent normalStudent = students.get(i);
            List<NormalCourse> courses = normalStudent.getCourses();
            double scoreSum = 0;
            double scoreCalculated = 0;
            for (int j = courses.size() - 1; j >= 0; j--) {
                NormalCourse course = courses.get(j);
                scoreCalculated += course.getCourseScore() * (course.getScore() / 10 - 5);
                scoreSum += course.getCourseScore();
            }
            if (scoreSum != 0) {
                normalStudent.setScorePoint(scoreCalculated / scoreSum);
            }
            for (int j = courses.size() - 1; j >= 0; j--) {
                NormalCourse course = courses.get(j);
                if (course.getExaminationScore() >= 42 && course.getScore() >= 60) courses.remove(j);
            }
            if (courses.isEmpty()) students.remove(i);
        }
        return students;
    }

    @Override
    public Object specialtyExtreme(String id, Integer specialtyId, String year, String term, Boolean all,Integer deptId, Integer grade, Integer classId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        List<NormalStudent> students = departmentGradeMapper.getFilterScore(specialtyId, year, term, all, deptId, grade, classId);
        double scorePointSum = 0;
        for (int i = 0; i < students.size(); i++) {
            NormalStudent normalStudent = students.get(i);
            List<NormalCourse> courses = normalStudent.getCourses();
            if (courses.isEmpty()) students.remove(i);
            double scoreSum = 0;
            double scoreCalculated = 0;
            for (int j = 0; j < courses.size(); j++) {
                NormalCourse course = courses.get(j);
                scoreCalculated += course.getCourseScore() * (course.getScore() / 10 - 5);
                scoreSum += course.getCourseScore();
            }

            if (scoreSum != 0){
                normalStudent.setScorePoint(scoreCalculated / scoreSum);
                scorePointSum += normalStudent.getScorePoint();
            }
        }
        Collections.sort(students, (o1, o2) -> (int) (o1.getScorePoint() - o2.getScorePoint()));

        Map<String, Object> res = new HashMap<>();
        if (! students.isEmpty()){
            res.put("min", students.get(0).getScorePoint());
            res.put("max", students.get(students.size() - 1).getScorePoint());
            res.put("maxStudent", students.get(students.size() - 1));
            res.put("avg", scorePointSum / students.size());
            res.put("minStudent", students.get(0));
        }
        return res;
    }

}
