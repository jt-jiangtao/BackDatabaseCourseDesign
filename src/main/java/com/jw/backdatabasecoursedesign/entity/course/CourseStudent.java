package com.jw.backdatabasecoursedesign.entity.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 22:20
 */
@Data
public class CourseStudent {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String specialtyName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String className;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String courseName;
}
