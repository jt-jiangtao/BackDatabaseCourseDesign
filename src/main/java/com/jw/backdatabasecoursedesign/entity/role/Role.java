package com.jw.backdatabasecoursedesign.entity.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jw.backdatabasecoursedesign.entity.user.User;
import lombok.Data;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 13:18
 */
@Data
public class Role {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roleName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> userList;
}
