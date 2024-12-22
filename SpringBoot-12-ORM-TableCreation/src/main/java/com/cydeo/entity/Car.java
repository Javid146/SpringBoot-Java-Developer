package com.cydeo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //tsble creation
@Table(name = "cars")//just to create our own table name
//to use data in DataGenerator class
@NoArgsConstructor
@Data
public class Car {

    @Id //for primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //will be added b postgres
    private String make;
    private String model;

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
    }
}
