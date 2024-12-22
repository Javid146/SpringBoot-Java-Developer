package com.cydeo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    //fetch = FetchType.LAZY is about performance. Let's say when you load the table merchant info is not needed. then LAZY will not
    //load it. It makes performance better. FetchType.EAGER loads all data each time.
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Payment> payment;

    public Customer(String firstName, String lastName, String username, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.address = address;
    }
}
