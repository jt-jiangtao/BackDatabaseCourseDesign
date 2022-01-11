package com.jw.backdatabasecoursedesign.entity.grade;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 13:59
 */
@Data
public class OrdinaryScoreItem {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer ordinaryScoreId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double proportion;

}
