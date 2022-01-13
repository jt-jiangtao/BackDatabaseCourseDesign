package com.jw.backdatabasecoursedesign.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.user.User;
import com.jw.backdatabasecoursedesign.mapper.UserMapper;
import com.jw.backdatabasecoursedesign.service.UserService;
import com.jw.backdatabasecoursedesign.utils.EntityToJson;
import com.jw.backdatabasecoursedesign.utils.JWTUtils;
import com.jw.backdatabasecoursedesign.utils.Type;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 13:01
 */
@Service
public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = null;

    public UserServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Override
    public Object login(String username, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserByUsername(username);
        if (Type.isNull(user) || !user.getPassword().equals(password)) return new UnifyResponse(1102);
        JSONObject res = EntityToJson.parse(user,"password");
        res.put("token", JWTUtils.createJWT(username));
        return res;
    }

    @Override
    public Object updatePassword(String username, String password, String newPassword) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int status = userMapper.updatePassword(username, password, newPassword);
        if (status <= 0) return new UnifyResponse(1104);
        sqlSession.commit();
        return new UnifyResponse(1020);
    }
}
