package com.cydeo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Human {

    private String name;
    private String surname;
    private String age;
    private String address;
    private String hobby;
}
