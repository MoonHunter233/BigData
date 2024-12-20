package com.syhbb.bigdata;

import com.syhbb.bigdata.spiderData.DO.comment.CommentData;
import com.syhbb.bigdata.util.Spider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


import java.util.List;

@SpringBootApplication
@EnableScheduling
public class BigDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigDataApplication.class, args);
    }

}
