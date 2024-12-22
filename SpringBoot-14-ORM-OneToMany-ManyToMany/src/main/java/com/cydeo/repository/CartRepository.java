package com.cydeo.repository;

import com.cydeo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /*
    there are different ways of creating query.
    todo Derive Query:
    by following certain naming convention (for ex. findById) JPA Query Builder creates SQL methods to retrieve data
    todo SQL Query:
    just normal sql queries
    todo JPQL Query:
    hibernate queries, similar to sql ones
    * */
}
