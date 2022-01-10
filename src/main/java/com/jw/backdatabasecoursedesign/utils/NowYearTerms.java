package com.jw.backdatabasecoursedesign.utils;

import java.util.Calendar;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 16:04
 */
public class NowYearTerms {

    public static String nowYear() {
        Calendar rightNow = Calendar.getInstance();
        Integer year = rightNow.get(Calendar.YEAR);
        Integer month = rightNow.get(Calendar.MONTH) + 1;
        if (month >= 9 && month <= 12) return year + "-" + (year + 1);
        else if (month >= 1 && month <= 2) return (year - 1) + "-" + year;
        else return (year - 1) + "-" + year;
    }

    public static String nowTerm() {
        Calendar rightNow = Calendar.getInstance();
        Integer month = rightNow.get(Calendar.MONTH) + 1;
        if (month >= 9 || month <= 2) return "第一学期";
        else return "第二学期";
    }
}
