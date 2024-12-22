package com.cydeo.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.math.BigDecimal;

@MappedSuperclass //when class is extended for other class and we do not want to create table from this super class
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //you let postgres create id auto.
    private Long id;
    private String owner;
    private BigDecimal balance;
    private BigDecimal interestRate;
}
