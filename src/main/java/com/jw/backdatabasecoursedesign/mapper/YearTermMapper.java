package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.yearTerm.YearTerm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 15:16
 */
@Mapper
public interface YearTermMapper {

    @Select("SELECT * FROM yearTerm;")
    List<YearTerm> selectAllYearTerm();

}
