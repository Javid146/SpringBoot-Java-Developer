package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employees")
public class Employee extends BaseEntity {

    String firstName;
    String lastName;
    String email;
    @Column(columnDefinition = "DATE")
    LocalDate hireDate;
    @Enumerated(EnumType.STRING)
    Gender gender;
    int salary;

    //OneToOne means 1 employee belongs to one department. for foreign key in table to join tables (department and employee)
    @OneToOne(cascade = CascadeType.ALL) //CascadeType.ALL means if you save this table entity, joined table entity (column) will change too
    //if you delete it from employees table, department table department_id will be deleted as well, if saved same happens as well
    @JoinColumn(name = "department_id") //represents foreign key column that joins to department
    private Department
            department; //we can get departmentId of employee with it

    @OneToOne(cascade = CascadeType.ALL)
    private Region region;

    public Employee(String firstName, String lastName, String email, LocalDate hireDate, Gender gender, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.gender = gender;
        this.salary = salary;
    }
}
