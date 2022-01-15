package com.jw.backdatabasecoursedesign.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jw.backdatabasecoursedesign.entity.role.Role;
import lombok.Data;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 13:15
 */
@Data
public class User {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @JsonIgnore
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Role> roleList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String identity;
}
