package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.mapper.DepartmentGradeMapper;
import com.jw.backdatabasecoursedesign.service.DepartmentInfoService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 22:20
 */
@Service
public class DepartmentInfoServiceImpl implements DepartmentInfoService {
    SqlSessionFactory sqlSessionFactory = null;

    public DepartmentInfoServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public Object departmentCourse(String teacherId, Integer specialtyId, String year, String term, Boolean all) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        return departmentGradeMapper.departmentCourse(teacherId, specialtyId, year, term, all);
    }

    @Override
    public Object departmentSpecialty(String teacherId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        return departmentGradeMapper.departmentSpecialty(teacherId);
    }

    @Override
    public Object departmentTeacher(String teacherId, Integer specialtyId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        return departmentGradeMapper.departmentTeacher(teacherId, specialtyId);
    }

    @Override
    public Object departmentClass(String teacherId, Integer specialtyId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        return departmentGradeMapper.departmentClass(teacherId, specialtyId);
    }
}
