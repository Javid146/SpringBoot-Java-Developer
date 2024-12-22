package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "regions")
public class Region extends BaseEntity {

    String region;
    String country;

    @OneToOne(mappedBy = "region")
    private Employee employee;

    public Region(String region, String country) {
        this.region = region;
        this.country = country;
    }
}
