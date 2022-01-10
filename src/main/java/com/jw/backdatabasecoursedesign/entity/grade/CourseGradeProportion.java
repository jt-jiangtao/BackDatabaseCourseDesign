package com.jw.backdatabasecoursedesign.entity.grade;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 22:41
 */
@Data
public class CourseGradeProportion {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double normalProportion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double examProportion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer courseId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherId;
}
