package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.mapper.SchoolDepartmentMapper;
import com.jw.backdatabasecoursedesign.service.SchoolDepartmentService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 21:05
 */
@Service
public class SchoolDepartmentServiceImpl implements SchoolDepartmentService {
    SqlSessionFactory sqlSessionFactory = null;

    public SchoolDepartmentServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public Object getAllDepartment() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SchoolDepartmentMapper schoolDepartmentMapper = sqlSession.getMapper(SchoolDepartmentMapper.class);
        List<Map<String, Object>> res = schoolDepartmentMapper.selectDept();
        if (res.isEmpty()) return new UnifyResponse(2001);
        return res;
    }
}
