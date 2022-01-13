package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.service.DepartmentStatisticClassService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: jiangtao
 * @Date: 2022/1/13 22:21
 */
@Service
public class DepartmentStatisticClassServiceImpl implements DepartmentStatisticClassService {
    SqlSessionFactory sqlSessionFactory = null;

    public DepartmentStatisticClassServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
}
