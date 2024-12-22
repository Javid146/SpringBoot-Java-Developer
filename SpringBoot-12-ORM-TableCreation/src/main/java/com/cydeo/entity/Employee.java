package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//this class will be used as source for db table
//otherwise we use schema2.sql and data.sql to create tables
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //postgres gives id automatically to each new object
    private int id;
    private String name;
}

// So we eaither create table schema via schema.sql file or via pojos
// data.sql file is inserting data inside of table
