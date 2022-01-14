package com.jw.backdatabasecoursedesign.config;

import com.jw.backdatabasecoursedesign.filter.TokenVerifyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: jiangtao
 * @Date: 2022/1/14 21:45
 */

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean filterRegister() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new TokenVerifyFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}
