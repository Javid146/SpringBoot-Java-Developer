package com.cydeo.repository;

import com.cydeo.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    //todo methods below are by Query Builder of JPA called Derived Query
    //on top of method provided by JPA, I can create my own methods
    List<Region> findByCountry(String country);
    //display all regions without duplicate
    List<Region> findDistinctByCountry(String country);
    //display all regions with country names that have "United"
    List<Region> findByCountryContaining(String country);
    //display all regions of a country in order
    List<Region> findByCountryContainingOrderByCountry(String country);
    //display top 2 regions in Canada
    List<Region> findTop2ByCountry(String country);
}

/*
IN ORDER TO INTERACT WITH DB CREATED, WE NEED TO CREATE REPOSITORY INTERFACES FOR CLASSES
*/
