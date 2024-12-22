package com.cydeo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "car_item_rel",
    joinColumns = @JoinColumn(name = "c_id"), //column name in current table
    inverseJoinColumns = @JoinColumn(name = "i_id")) //same column name in other joined table
    private List<Item> itemList;
}

