package com.jw.backdatabasecoursedesign.entity.right;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 23:07
 */
@Data
public class UserRight {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String identity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Role> roles;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> allRoles;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> allAccesses;
}
