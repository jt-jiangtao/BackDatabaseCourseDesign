package com.jw.backdatabasecoursedesign.service.impl;

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
            return map;
        }
        return courseGradeProportion;
    }

}
