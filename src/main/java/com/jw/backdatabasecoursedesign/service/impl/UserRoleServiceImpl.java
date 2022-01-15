package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.entity.right.UserRight;
import com.jw.backdatabasecoursedesign.mapper.UserRoleMapper;
import com.jw.backdatabasecoursedesign.service.UserRoleService;
import com.jw.backdatabasecoursedesign.utils.NowYearTerms;
import com.jw.backdatabasecoursedesign.utils.UserRightParseUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 23:15
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    SqlSessionFactory sqlSessionFactory = null;

    public UserRoleServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public UserRight getUserRight(String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserRoleMapper userRoleMapper = sqlSession.getMapper(UserRoleMapper.class);
        UserRight userRight = userRoleMapper.getUserRight(id);
        return userRight == null ? null : UserRightParseUtils.parse(userRight);
    }

    @Override
    public Boolean deptManagerHasAccessToStudent(String studentId, String teacherId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserRoleMapper userRoleMapper = sqlSession.getMapper(UserRoleMapper.class);
        int count = userRoleMapper.DeptManagerHasAccessToStudent(studentId, teacherId);
        return count == 1;
    }

    @Override
    public Boolean deptManagerHasAccessToDept(String id, String deptId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserRoleMapper userRoleMapper = sqlSession.getMapper(UserRoleMapper.class);
        int count = userRoleMapper.deptManagerHasAccessToDept(id, deptId);
        return count == 1;
    }

    @Override
    public boolean courseCanInput(String courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserRoleMapper userRoleMapper = sqlSession.getMapper(UserRoleMapper.class);
        int count = userRoleMapper.courseYearTerm(courseId, NowYearTerms.nowYear(), NowYearTerms.nowTerm());
        return count == 1;
    }

    @Override
    public Boolean sameDept(String teacherId, String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserRoleMapper userRoleMapper = sqlSession.getMapper(UserRoleMapper.class);
        int count = userRoleMapper.sameDept(teacherId, id);
        return count == 1;
    }
}
