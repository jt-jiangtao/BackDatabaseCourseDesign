package com.jw.backdatabasecoursedesign.config;

import com.jw.backdatabasecoursedesign.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 21:48
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/student/**")
                .addPathPatterns("/teacher/**")
                .addPathPatterns("/school/**")
                .addPathPatterns("/dept/**");
    }
}
