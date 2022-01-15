package com.jw.backdatabasecoursedesign.utils;

import com.jw.backdatabasecoursedesign.entity.right.Access;
import com.jw.backdatabasecoursedesign.entity.right.Role;
import com.jw.backdatabasecoursedesign.entity.right.UserRight;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 23:58
 */
public class UserRightParseUtils {

    public static UserRight parse(UserRight userRight) {
        List<String> allRoles = new ArrayList<>();
        List<String> allAccesses = new ArrayList<>();
        List<Role> roles = userRight.getRoles();
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            if (! allRoles.contains(role.getRoleName())) {
                allRoles.add(role.getRoleName());
                List<Access> accesses = role.getAccesses();
                for (int j = 0; j < accesses.size(); j++) {
                    Access access = accesses.get(j);
                    if (! allAccesses.contains(access.getAccessName())){
                        allAccesses.add(access.getAccessName());
                    }
                }
            }
        }
        userRight.setAllRoles(allRoles);
        userRight.setAllAccesses(allAccesses);
        return userRight;
    }

}
