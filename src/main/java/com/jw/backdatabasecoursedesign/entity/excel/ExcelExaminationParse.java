package com.jw.backdatabasecoursedesign.entity.excel;

import com.jw.backdatabasecoursedesign.entity.grade.ExaminationStudentScore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: jiangtao
 * @Date: 2022/1/12 20:06
 */
@Data
@AllArgsConstructor
public class ExcelExaminationParse {
    private List<ExaminationStudentScore> success;

    private List<Map<String, Object>> error;
}
