package com.cydeo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DealerApp {

    public static void main(String[] args) {

        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigCar.class);

        Car c = container.getBean(Car.class);
        Person P  = container.getBean(Person.class);

        System.out.println("car make = " + c.getMake());
        System.out.println("Person name = " + P.getName());
        System.out.println("Person car name = " + P.getCar().getMake());
    }
}
