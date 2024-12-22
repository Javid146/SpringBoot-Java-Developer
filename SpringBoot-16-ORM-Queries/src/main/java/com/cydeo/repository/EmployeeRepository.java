package com.cydeo.repository;

import com.cydeo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //todo methods below are by Query Builder of JPA called Derived Query
    List<Employee> findByEmail(String email);
    List<Employee> findByFirstNameAndLastNameOrEmail(String firstName, String lastName, String email);
    List<Employee> findByFirstNameIsNot(String firstName);
    List<Employee> findByLastNameStartingWith(String pattern);
    List<Employee> findBySalaryGreaterThan(Integer salary);
    List<Employee> findBySalaryLessThan(Integer salary);
    List<Employee> findBySalaryLessThanEqual(Integer salary);
    List<Employee> findByHireDateBetween(LocalDate start, LocalDate end);
    List<Employee> findBySalaryGreaterThanEqualOrderBySalaryDesc(Integer salary);
    List<Employee> findTop3BySalaryLessThan(Integer salary);
    List<Employee> findByEmailIsNull();

    //TODO JPQL (java persistent query language), interacts with entity objects not db directly
    //below Employee is name of Employee class, not name of db table
    //method name can be anything and method should have return type
    @Query("select e from Employee e where e.email= 'fdellatorrequ@soundcloud.com'")
    Employee findEmployeeDetail();

    //return integer
    @Query("select a.salary from Employee a where a.email='fdellatorrequ@soundcloud.com'")
    Integer getEmployeeSalaryByEmail();

    //todo positional query
    //you want to find email and salary. position of instances in query should be same as in method signature
    //if email positione 1st (?1), should first here too: getEmployeeDetail(String email, int salary); and vice versa
    @Query("select c from Employee c where c.email=?1 and c.salary=?2")
    Employee getEmployeeDetail(String email, int salary);

    @Query("select c from Employee c where c.email=?1")
    Optional <Employee> getEmployeeDetail(String email);

    //todo not equal
    @Query("select a from Employee a where a.salary <> ?1")
    Employee getEmployeeSalary(Integer salary);

    //todo contains/starts/endsWith/like
    @Query("select e from Employee e where e.firstName like ?1")
    List<Employee> findEmployeeByFirstNameLike(String firstName);

    //before
    @Query("select e from Employee e where e.hireDate < ?1")
    List<Employee> findEmployeeHireDateBefore(LocalDate date);

    //todo between
    @Query("select e from Employee e where e.salary between ?1 and ?2")
    List<Employee> findEmployeeSalaryBetween(int salary1, int salary);

    //todo null
    @Query("select e from Employee e where e.email is null")
    List<Employee> findEmployeeNullEmail();

    //todo not null
    @Query("select e from Employee e where e.email is not null")
    List<Employee> findEmployeeNotNullEmail();

    //sort ascending
    @Query("select e from Employee e order by e.salary")
    List<Employee> findEmployeeBySalaryAsc();

    //sort descending
    @Query("select e from Employee e order by e.salary desc")
    List<Employee> findEmployeeBySalaryDesc();


    //todo @QUERY IS POSSIBLE IN TWO WAYS: 1 WITH JPQL EXAMPLES ABOVE. 2 WITH NATIVE SQL QUERIES. SEE BELOW
    //NATIVE SQL QUERIES ARE PURE SQL WITH @QUERY ANNOT. JPQL INTERACTS WITH ENTITY OBJECT (CLASSES), NATIVE SQL INTERACTS DIRECTLY WITH DB TABLES
    //todo employees here should have same name as employees table name
    //native sql also has 2 types: positioned parameters (?1, ?2) where position of parameter is important and named parameters where position is not
    @Query(value = "select * from employees where salary ?1", nativeQuery = true)
    List<Employee> findEmployeeBySalary(int salary);

    //todo named parameters of JPQL (interacts with class not db table, hence it Employee not employees)
    @Query("select e from Employee e where e.salary = :salary")
    List<Employee> findEmployeeBySalaryNamedParam(@Param("salary") int salary);


    //todo UPDATE/DELETE WE NEED TO USE @MODIFYING AND @TRANSACTIONAL. WORKS WITH JPQL AND NATIVE SQL
    @Modifying //native
    @Transactional
    @Query(value = "update employees set email = 'jmammadli@bclc.com' where id = :id", nativeQuery = true)
    void updateEmployeeWithNativeQuery(@Param("id") int id);

    @Modifying //JPQL
    @Transactional
    @Query(value = "update Employee e set e.email = 'jmammadli@bclc.com' where e.id = :id")
    void updateEmployeeWithJPQL(@Param("id") int id);
}
