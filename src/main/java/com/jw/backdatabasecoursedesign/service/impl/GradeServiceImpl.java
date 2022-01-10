package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.entity.grade.CompositeGrade;
import com.jw.backdatabasecoursedesign.mapper.GradeMapper;
import com.jw.backdatabasecoursedesign.service.GradeService;
import com.jw.backdatabasecoursedesign.utils.Type;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 16:36
 */
@Service
public class GradeServiceImpl implements GradeService {
    SqlSessionFactory sqlSessionFactory = null;

    public GradeServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Override
    public Object studentNormalScore(String id, String year, String term, boolean all) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GradeMapper gradeMapper = sqlSession.getMapper(GradeMapper.class);
        return gradeMapper.selectNormalGrade(id, year, term, all);
    }

    @Override
    public Object studentAllScore(String id, String year, String term, boolean all) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GradeMapper gradeMapper = sqlSession.getMapper(GradeMapper.class);
        return gradeMapper.selectCompositeGrade(id, year, term, all);
    }

    @Override
    public Object studentGPA(String id, String year, String term, boolean all) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GradeMapper gradeMapper = sqlSession.getMapper(GradeMapper.class);
        List<CompositeGrade> compositeGradeList = gradeMapper.selectCompositeGrade(id, year, term, all);
        Map<String, Object> res = new HashMap();
        if (Type.isNullList(compositeGradeList)){
            res.put("gpaStatus", "该学生(" + id + ")没有考试成绩，无法计算GPA");
            return res;
        }
        double scoreGPA = 0;
        double scoreSum = 0;
        for (int i = 0; i < compositeGradeList.size(); i++) {
            CompositeGrade compositeGrade = compositeGradeList.get(i);
            scoreGPA += compositeGrade.getScore() * (compositeGrade.getGrade()/10 - 5);
            scoreSum += compositeGrade.getScore();
        }
        res.put("gpa", scoreGPA / scoreSum);
        return res;
    }
}
