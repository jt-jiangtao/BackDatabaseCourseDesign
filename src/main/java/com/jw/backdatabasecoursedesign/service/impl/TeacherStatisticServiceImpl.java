package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.mapper.TeacherGradeMapper;
import com.jw.backdatabasecoursedesign.service.TeacherStatisticService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 18:33
 */
@Service
public class TeacherStatisticServiceImpl implements TeacherStatisticService {
    SqlSessionFactory sqlSessionFactory = null;

    public TeacherStatisticServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public Object courseFailInfo(String id, Integer courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        return teacherGradeMapper.selectCourseFailStudent(id, courseId);
    }

    @Override
    public Object courseExtremeInfo(String id, Integer courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        return teacherGradeMapper.selectMinMaxAvg(id, courseId);
    }

    @Override
    public Object putUnfinishedInfo(String id, Integer courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        Map<String, Object> res = new HashMap<>();
        res.put("examination", teacherGradeMapper.teacherUndoExaminationScore(id, courseId));
        res.put("ordinaryScoreItem", teacherGradeMapper.teacherUndoOrdinaryItemScore(id, courseId));
        res.put("ordinaryScore", teacherGradeMapper.teacherUndoStudentOrdinaryScore(id, courseId));
        return res;
    }
}
