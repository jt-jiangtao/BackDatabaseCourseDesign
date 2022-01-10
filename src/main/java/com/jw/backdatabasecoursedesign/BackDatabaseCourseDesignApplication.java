package com.jw.backdatabasecoursedesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ServletComponentScan
public class BackDatabaseCourseDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackDatabaseCourseDesignApplication.class, args);
    }

}
