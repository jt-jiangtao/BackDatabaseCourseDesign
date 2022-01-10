package com.jw.backdatabasecoursedesign.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 14:42
 */
public class Type {
    public static boolean isNull(Object obj){
        return obj == null;
    }

    public static boolean isNullList(Object obj){
        if (obj instanceof List) return ((List<?>) obj).size() == 0;
        return obj == null;
    }
}
