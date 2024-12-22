package com.cydeo.component_annotation;

import org.springframework.stereotype.Component;

@Component //@ComponentScan annot. will look for this annot.
public class Selenium {

    public void getTeachingHours(){

        System.out.println("Total teaching hours: 100");
    }
}
