package com.cydeo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {

    private String name;
    private Car car; //dependency injection or has-a relation. Having 1 object (car) in another (person)
}
