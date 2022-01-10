package com.jw.backdatabasecoursedesign.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 15:39
 */
public class EntityToJson {
    public static JSONObject parse(Object obj,String ... args){
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(obj));
        for (String arg : args) {
            jsonObject.remove(arg);
        }
        return jsonObject;
    }
}
