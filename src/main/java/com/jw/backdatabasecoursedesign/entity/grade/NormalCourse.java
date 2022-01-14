package com.jw.backdatabasecoursedesign.entity.grade;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 19:19
 */
@Data
public class NormalCourse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String year;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String term;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String courseName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String property;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double courseScore;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studyMode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double score;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer courseId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String grade;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double examinationScore;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double ordinaryScoreProportion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double examinationProportion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer examinationId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer studentExaminationId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double ordinaryScore;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer teacherDeptId;

}
