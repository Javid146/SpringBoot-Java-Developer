package com.cydeo.component_annotation;

import org.springframework.stereotype.Component;

@Component //@ComponentScan annot. will look for this annot.
public class Java {

    public void getTeachingHours(){

        System.out.println("Total teaching hours: 250");
    }
}
