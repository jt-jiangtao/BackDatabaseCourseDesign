package com.jw.backdatabasecoursedesign.config;

import com.jw.backdatabasecoursedesign.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 21:48
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RoleAccessInterceptor getRoleInterceptor(){
        return new RoleAccessInterceptor();
    }

    @Bean
    public StudentAccessInterceptor getStudentAccessInterceptor(){
        return new StudentAccessInterceptor();
    }

    @Bean
    public TeacherAccessInterceptor getTeacherAccessInterceptor(){
        return new TeacherAccessInterceptor();
    }

    @Bean
    public DeptAccessInterceptor getDeptAccessInterceptor(){
        return new DeptAccessInterceptor();
    }

    @Bean
    public SchoolAccessInterceptor getSchoolAccessInterceptor(){
        return new SchoolAccessInterceptor();
    }

    @Bean GradeInputTimeAccessInterceptor getGradeInputTimeAccessInterceptor(){
        return new GradeInputTimeAccessInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/student/**")
                .addPathPatterns("/teacher/**")
                .addPathPatterns("/school/**")
                .addPathPatterns("/dept/**")
                .order(1);

        registry.addInterceptor(getRoleInterceptor())
                .addPathPatterns("/student/**")
                .addPathPatterns("/teacher/**")
                .addPathPatterns("/school/**")
                .addPathPatterns("/dept/**")
                .order(2);

        registry.addInterceptor(getStudentAccessInterceptor())
                .addPathPatterns("/student/**")
                .order(3);

        registry.addInterceptor(getGradeInputTimeAccessInterceptor())
                .addPathPatterns("/teacher/proportion/update")
                .addPathPatterns("/teacher/normalScore/item/update")
                .addPathPatterns("/teacher/normalScore/download/excel")
                .addPathPatterns("/teacher/normalScore/upload/excel")
                .addPathPatterns("/teacher/normalScore/upload/sql")
                .addPathPatterns("/teacher/normalScore/add")
                .addPathPatterns("/teacher/normalScore/delete")
                .addPathPatterns("/teacher/normalScore/update")
                .addPathPatterns("/teacher/examScore/download/excel")
                .addPathPatterns("/teacher/examScore/upload/excel")
                .addPathPatterns("/teacher/examScore/upload/sql")
                .addPathPatterns("/teacher/examScore/add")
                .addPathPatterns("/teacher/examScore/delete")
                .addPathPatterns("/teacher/examScore/update")
                .order(4);
        registry.addInterceptor(getTeacherAccessInterceptor())
                .addPathPatterns("/teacher/**")
                .order(5);

        registry.addInterceptor(getDeptAccessInterceptor())
                .addPathPatterns("/dept/**")
                .order(6);

        registry.addInterceptor(getSchoolAccessInterceptor())
                .addPathPatterns("/dept/**")
                .order(7);
    }
}
