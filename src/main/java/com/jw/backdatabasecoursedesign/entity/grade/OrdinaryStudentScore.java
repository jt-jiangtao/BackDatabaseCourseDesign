package com.jw.backdatabasecoursedesign.entity.grade;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: jiangtao
 * @Date: 2022/1/12 0:06
 */
@Data
@AllArgsConstructor
public class OrdinaryStudentScore {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double score;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer OrdinaryScoreItemId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentId;
}
