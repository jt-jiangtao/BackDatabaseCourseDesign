package com.jw.backdatabasecoursedesign.entity.grade;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 19:19
 */
@Data
public class NormalStudent {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String specialtyName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String className;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer studentSpecialtyId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer studentClassId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer nowGrade;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double scorePoint;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<NormalCourse> courses;

}
