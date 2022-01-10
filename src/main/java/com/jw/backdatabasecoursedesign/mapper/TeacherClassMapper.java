package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.course.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 21:48
 */
@Mapper
public interface TeacherClassMapper {

    @Select("SELECT * FROM course AS c, yearTerm AS y WHERE c.yearTermId = y.id AND year = #{year} AND term = #{term} AND teacherId = #{id} AND status = 1")
    List<Course> selectTeacherCourse(String id, String year,String term);

}
