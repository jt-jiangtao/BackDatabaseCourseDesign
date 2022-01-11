package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.grade.CourseGradeProportion;
import com.jw.backdatabasecoursedesign.mapper.TeacherGradeMapper;
import com.jw.backdatabasecoursedesign.service.TeacherProportionService;
import com.jw.backdatabasecoursedesign.utils.Type;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 22:26
 */
@Service
public class TeacherProportionServiceImpl implements TeacherProportionService {

    SqlSessionFactory sqlSessionFactory = null;

    public TeacherProportionServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public Object getCourseProportion(Integer courseId, String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        CourseGradeProportion courseGradeProportion = teacherGradeMapper.selectCourseProportion(courseId, id);
        if (Type.isNull(courseGradeProportion)){
            Map<String, Object> map = new HashMap<>();
            map.put("status", "该教师不存在改门课程");
            return new UnifyResponse(1701, map);
        }
        return courseGradeProportion;
    }

    @Override
    public Object updateCourseProportion(Integer courseId, String id, Double normalProportion, Double examProportion) {
        Map<String, Object> map = new HashMap<>();
        if (normalProportion + examProportion != 1){
            map.put("status", "平时成绩和考试成绩想相加比例必须为1");
            return new UnifyResponse(1702, map);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        Integer normalRes = teacherGradeMapper.updateOrdinaryScore(id, courseId, normalProportion);
        Integer examRes = teacherGradeMapper.updateExaminationScore(id, courseId, examProportion);
        if (normalRes == 1 && examRes == 1){
            sqlSession.commit();
            sqlSession.close();
            map.put("id", courseId);
            map.put("normalProportion", normalProportion);
            map.put("examProportion", examProportion);
            return map;
        }
        sqlSession.rollback();
        map.put("status", "更新失败, 可能是该教师没有改门课程，请检查课程号");
        return new UnifyResponse(1703, map);
    }

}
