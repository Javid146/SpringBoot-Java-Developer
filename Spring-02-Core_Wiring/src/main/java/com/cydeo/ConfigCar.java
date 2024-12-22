package com.cydeo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCar {

    @Bean
    Car car(){
        Car c = new Car();
        c.setMake("Honda");
        return c;}

    //direct wiring
//    @Bean
//    Person person(){
//        Person p = new Person();
//        p.setName("Javid");
          //dependency injection or has-a relation. Having 1 object (car) in another (person)
//        p.setCar(car()); //setting Honda above as Javid's car. It is called direct wiring
//        return p;}

    //auto-wiring
    @Bean
    Person person(Car car){
        Person p = new Person();
        p.setName("Javid");
        p.setCar(car); //setting Honda above as Javid's car. //dependency injection or has-a relation. Having 1 object (car) in another (person)
        return p;}
}
