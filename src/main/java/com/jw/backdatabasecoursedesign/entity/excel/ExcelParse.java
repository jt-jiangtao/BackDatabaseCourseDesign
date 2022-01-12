package com.jw.backdatabasecoursedesign.entity.excel;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: jiangtao
 * @Date: 2022/1/12 12:25
 */
@Data
@AllArgsConstructor
public class ExcelParse {

    private List<Map<String, Object>> success;

    private List<Map<String, Object>> error;

}
