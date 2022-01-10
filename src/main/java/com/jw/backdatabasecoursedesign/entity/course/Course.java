package com.jw.backdatabasecoursedesign.entity.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jw.backdatabasecoursedesign.entity.yearTerm.YearTerm;
import lombok.Data;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 21:52
 */
@Data
public class Course {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String startTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String endTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double score;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String introduce;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String property;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer classNumber;

}
