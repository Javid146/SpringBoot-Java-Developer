package com.cydeo.repository;

import com.cydeo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //todo methods below are by Query Builder of JPA called Derived Query
    List<Department> findByDepartment(String department);
    //all 3 are same
    List<Department> findByDivision(String division);
    List<Department> findByDivisionIs(String division);
    List<Department> findByDivisionEquals(String division);
    List<Department> findDistinctTop3ByDivisionContains(String pattern);

    //In
    @Query("select a from Department a where a.division in ?1")
    List<Department> findDepartmentDivisionIn(List<String> departments);

    //TODO NAMED QUERY STEPS GO TO RESOURCES>CREATE META-INF FOLDER>CREATE FILE jpa-named-queries.properties
    //ADD ENTITYNAME.METHODNAME (Department.retrieveDepartmentByDivision)
    //todo NAMED QUERIES CAN BE JPQL OR NATIVE SQL
    List<Department> retrieveDepartmentByDivision(String division);  //JPQL

    @Query(nativeQuery = true)  //NATIVE SQL
    List<Department>retrieveDepartmentByDivisionContains(String pattern);
}
