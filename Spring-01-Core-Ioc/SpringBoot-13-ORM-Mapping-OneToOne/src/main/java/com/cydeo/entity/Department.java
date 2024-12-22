package com.cydeo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    String department;
    String division;

    /*means have a foreign key as in employees table, but do not show in db table, instead let table created by
    * Employee class has that foreign key column in table.mappedBy = "department" has to match with private Department department;
     in Employee class. Should be same name. This annot helps to access employees table data from departments*/
    @OneToOne(mappedBy = "department")
    private Employee employee;

    public Department(String department, String division) {
        this.department = department;
        this.division = division;
    }
}

