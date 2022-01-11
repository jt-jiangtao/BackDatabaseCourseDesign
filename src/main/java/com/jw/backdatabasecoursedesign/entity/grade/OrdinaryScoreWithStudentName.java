package com.jw.backdatabasecoursedesign.entity.grade;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 17:28
 */
@Data
public class OrdinaryScoreWithStudentName {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String year;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String term;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String courseName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String typeName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double score;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double proportionInCourse;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double proportionInOrdinary;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer itemId;
}
