package com.cydeo.repository;

import com.cydeo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    //this will allow us to use all methods within JpaRepository provided by Spring
    //but if we want to create a method that is not in JpaRepository then we can create that here
    //repository Interfaces always work with entity > classes not DTO classes

    //if methods provided by JpaRepository is not enough you can create your own methods in this Interface
}
