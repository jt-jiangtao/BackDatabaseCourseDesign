package com.jw.backdatabasecoursedesign.entity.right;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 23:07
 */
@Data
public class Role {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer roleId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roleName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Access> accesses;

}
