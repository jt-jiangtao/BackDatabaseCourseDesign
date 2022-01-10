package com.jw.backdatabasecoursedesign.entity.grade;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 17:34
 */
@Data
public class CompositeGrade {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String year;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String term;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String courseName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String property;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double score;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studyMode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double grade;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer courseId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String r;
}
