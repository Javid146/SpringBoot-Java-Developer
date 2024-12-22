package com.cydeo.component_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CydeoApp {

    public static void main(String[] args) {

        //create container and add config class class in it.
        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigCourse.class);

        container.getBean(Java.class).getTeachingHours();
        container.getBean(Selenium.class).getTeachingHours();
        container.getBean(Agile.class).getTeachingHours();
    }
}
