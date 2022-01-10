package com.jw.backdatabasecoursedesign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.user.User;
import com.jw.backdatabasecoursedesign.mapper.UserMapper;
import com.jw.backdatabasecoursedesign.mapper.YearTermMapper;
import com.jw.backdatabasecoursedesign.service.UserService;
import com.jw.backdatabasecoursedesign.service.YearTermService;
import com.jw.backdatabasecoursedesign.utils.EntityToJson;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import com.jw.backdatabasecoursedesign.utils.NowYearTerms;
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
 * @Date: 2022/1/10 13:01
 */
@Service
public class YearTermServiceImpl implements YearTermService {
    SqlSessionFactory sqlSessionFactory = null;

    public YearTermServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public Object getAllYearTerm() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        YearTermMapper yearTermMapper = sqlSession.getMapper(YearTermMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("yearTerms", yearTermMapper.selectAllYearTerm());
        map.put("year", NowYearTerms.nowYear());
        map.put("term", NowYearTerms.nowTerm());
        return map;
    }
}
