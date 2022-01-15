package com.jw.backdatabasecoursedesign.entity.right;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 23:11
 */
@Data
public class Access {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer accessId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessName;

}
