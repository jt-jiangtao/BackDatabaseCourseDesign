package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.grade.CompositeGrade;
import com.jw.backdatabasecoursedesign.entity.grade.NormalGrade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 16:34
 */
@Mapper
public interface GradeMapper {

    List<NormalGrade> selectNormalGrade(String id, String year, String term, boolean all);

    List<CompositeGrade> selectCompositeGrade(String id, String year, String term, boolean all);

}
