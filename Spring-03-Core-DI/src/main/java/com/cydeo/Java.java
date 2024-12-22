package com.cydeo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Java {

    //1. @Autowired //FIELD DEPENDENCY INJECTION
    //OfficeHours officeHours;

    //2. CONSTRUCTOR DEPENDENCY INJECTION
    OfficeHours officeHours;

    @Autowired
    public Java(OfficeHours officeHours){
        this.officeHours=officeHours;
    }
    public void getTeachingHours(){
        System.out.println("Weekly teaching hours: "+ (20+officeHours.getHours()));
    }
}
