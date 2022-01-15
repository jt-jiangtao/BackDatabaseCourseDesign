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
    public Object departmentCourse(Integer specialtyId, String year, String term, Boolean all, Integer deptId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        return departmentGradeMapper.departmentCourse(specialtyId, year, term, all, deptId);
    }

    @Override
    public Object departmentSpecialty(Integer deptId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        return departmentGradeMapper.departmentSpecialty(deptId);
    }

    @Override
    public Object departmentTeacher(Integer specialtyId, Integer deptId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        return departmentGradeMapper.departmentTeacher(specialtyId, deptId);
    }

    @Override
    public Object departmentClass(Integer specialtyId, Integer deptId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentGradeMapper departmentGradeMapper = sqlSession.getMapper(DepartmentGradeMapper.class);
        return departmentGradeMapper.departmentClass(specialtyId, deptId);
    }
}
