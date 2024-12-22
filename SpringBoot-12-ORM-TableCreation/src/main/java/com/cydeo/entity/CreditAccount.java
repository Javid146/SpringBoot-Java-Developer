package com.cydeo.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class CreditAccount extends Account {

    BigDecimal creditLimit;
}


//no more data is needed here as we extend and define all in Account class