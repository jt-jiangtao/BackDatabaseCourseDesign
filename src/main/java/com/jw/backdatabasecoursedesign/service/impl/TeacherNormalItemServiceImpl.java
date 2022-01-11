package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.course.Course;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreItem;
import com.jw.backdatabasecoursedesign.mapper.TeacherGradeMapper;
import com.jw.backdatabasecoursedesign.service.TeacherNormalItemService;
import com.jw.backdatabasecoursedesign.utils.Type;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 14:27
 */
@Service
public class TeacherNormalItemServiceImpl implements TeacherNormalItemService {
    SqlSessionFactory sqlSessionFactory = null;

    public TeacherNormalItemServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Override
    public Object updateNormalItem(String id, Integer courseId, List<OrdinaryScoreItem> items) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);

        List<OrdinaryScoreItem> existedItems = teacherGradeMapper.selectOrdinaryItems(id, courseId);
        if (existedItems == null){
            Map<String, Object> map = new HashMap<>();
            map.put("status", "插入数据失败");
            return new UnifyResponse(1501, map);
        }else if (!Type.isNullList(existedItems)){
            // 删除原来的成绩项
            List<Integer> list = new ArrayList<>();
            for (OrdinaryScoreItem item : existedItems) {
                list.add(item.getId());
            }
            int status = teacherGradeMapper.deleteOrdinaryItem(list);
            if (status != list.size()){
                sqlSession.rollback();
                Map<String, Object> map = new HashMap<>();
                map.put("status", "插入数据失败");
                return new UnifyResponse(1501, map);
            }
        }

        double proportionSum = 0;
        for (OrdinaryScoreItem item : items) {
            proportionSum += item.getProportion();
        }
        if (proportionSum != 1){
            Map<String, Object> map = new HashMap<>();
            map.put("status", "修改之后的比例不为1");
            return new UnifyResponse(1502, map);
        }

        int status = teacherGradeMapper.addOrdinaryItem(id, courseId, items);
        if (status != items.size()){
            sqlSession.rollback();
            Map<String, Object> map = new HashMap<>();
            map.put("status", "插入数据失败");
            return new UnifyResponse(1501, map);
        }
        sqlSession.commit();
        return items;
    }

    @Override
    public Object getNormaItem(String id, Integer courseId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        List<OrdinaryScoreItem> items = teacherGradeMapper.selectOrdinaryItems(id, courseId);
        if (Type.isNullList(items)){
            Map<String, Object> map = new HashMap<>();
            map.put("status", "查询到结果为空，可能是课程号不正确");
            return new UnifyResponse(1503, map);
        }
        return items;
    }
}
