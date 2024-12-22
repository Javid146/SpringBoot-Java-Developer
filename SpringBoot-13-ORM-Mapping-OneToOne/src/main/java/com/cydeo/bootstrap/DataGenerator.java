package com.cydeo.bootstrap;

import com.cydeo.entity.Department;
import com.cydeo.entity.Employee;
import com.cydeo.entity.Region;
import com.cydeo.enums.Gender;
import com.cydeo.repository.DepartmentRepository;
import com.cydeo.repository.EmployeeRepository;
import com.cydeo.repository.RegionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataGenerator implements CommandLineRunner {

//    DepartmentRepository departmentRepository;
    EmployeeRepository employeeRepository;
    RegionRepository regionRepository;

    public DataGenerator(EmployeeRepository employeeRepository) {
//        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Department department1 = new Department("IT", "Information");
        Department department2 = new Department("Labor", "Landscaping");
        Department department3 = new Department("Tools", "Hardware");
        Department department4 = new Department("Computers", "Electronics");

        Region region1 = new Region("America", "Mexico");
        Region region2 = new Region("Europe", "Europe");
        Region region3 = new Region("Africa", "Tunisia");
        Region region4 = new Region("America", "Canada");

        Employee employee1 = new Employee("Javid","Mammadli", "jmammadli@bclc.com", LocalDate.of( 2023,06,18), Gender.MALE, 50000);
        Employee employee2 = new Employee("Nijat","Mammadli", "nmammadli@bclc.com", LocalDate.of( 2020,03,15), Gender.MALE, 100000);
        Employee employee3 = new Employee("Julie","Seb", "jseb@bclc.com", LocalDate.of( 2020,05,05), Gender.FEMALE, 100000);
        Employee employee4 = new Employee("Twain","Mark", "tmark@bclc.com", LocalDate.of( 2020,01,01), Gender.MALE, 60000);

        //employee saves its own department so will show in table. employee and department has hasA relation
        employee1.setDepartment(department1);
        employee2.setDepartment(department2);
        employee3.setDepartment(department3);
        employee4.setDepartment(department4);

        //we can do it because employee and region has hasA relation
        employee1.setRegion(region1);
        employee2.setRegion(region2);
        employee3.setRegion(region3);
        employee4.setRegion(region4);

        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employee1, employee2, employee3, employee4));
        employeeRepository.saveAll(employeeList);

    }
}
