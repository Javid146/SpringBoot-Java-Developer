package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

//this class will be used as source for db table
//otherwise we use schema2.sql and data.sql to create tables
@Entity
@Table(name = "students") //if you want to create your own table name
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //postgres gives id automatically to each new object
    //even if you did not add id manually in data.sql
    private long id;

    @Column(name = "studentFirstName") //if you want to create your own column name
    private String firstName;
    private String lastName;
    private String email;

    @Transient //if you do not want column appear on table
    private String city;

    @Column(columnDefinition = "DATE")
    private LocalDate birthDate;
    @Column(columnDefinition = "TIME")
    private LocalTime birthTime;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate birthDateTime;

    @Enumerated(EnumType.STRING) //without it enum shows as integer
    private Gender gender;
}

// So we either create table schema via schema.sql file or via pojos
// data.sql file is inserting data inside of table
