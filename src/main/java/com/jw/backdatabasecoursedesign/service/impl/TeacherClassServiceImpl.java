package com.jw.backdatabasecoursedesign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jw.backdatabasecoursedesign.entity.course.Course;
import com.jw.backdatabasecoursedesign.mapper.TeacherClassMapper;
import com.jw.backdatabasecoursedesign.service.TeacherClassService;
import com.jw.backdatabasecoursedesign.utils.EntityToJson;
import com.jw.backdatabasecoursedesign.utils.Type;
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
 * @Date: 2022/1/10 21:49
 */
@Service
public class TeacherClassServiceImpl implements TeacherClassService {
    SqlSessionFactory sqlSessionFactory = null;

    public TeacherClassServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public Object getTeacherClass(String id, String year, String term) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherClassMapper teacherClassMapper = sqlSession.getMapper(TeacherClassMapper.class);
        List<Course> courses = teacherClassMapper.selectTeacherCourse(id, year, term);
        Map<String, Object> map = new HashMap<>();
        if (Type.isNullList(courses)){
            map.put("status", "查询到的结果为空");
            return map;
        }
        map.put("courses", courses);
        map.put("year", year);
        map.put("term", term);
        return map;
    }
}
