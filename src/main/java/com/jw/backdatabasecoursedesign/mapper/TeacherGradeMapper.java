package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.course.Course;
import com.jw.backdatabasecoursedesign.entity.grade.CourseGradeProportion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 21:48
 */
@Mapper
public interface TeacherGradeMapper {

    @Select("SELECT c.name ,o.proportion AS normalProportion, e.proportion AS examProportion, e.courseId, teacherId  FROM course AS c, ordinaryScore AS o, examination AS e WHERE c.id = o.courseId AND c.id = e.courseId AND e.courseId = #{courseId} AND teacherId = #{id};")
    CourseGradeProportion selectCourseProportion(Integer courseId, String id);

}
