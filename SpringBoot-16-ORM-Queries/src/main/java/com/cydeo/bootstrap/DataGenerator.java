package com.cydeo.bootstrap;

import com.cydeo.repository.CourseRepository;
import com.cydeo.repository.DepartmentRepository;
import com.cydeo.repository.EmployeeRepository;
import com.cydeo.repository.RegionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component //to make it bean
public class DataGenerator implements CommandLineRunner {

    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final CourseRepository courseRepository;

    public DataGenerator(RegionRepository regionRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository,
                         CourseRepository courseRepository) {
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(" ------------------- REGION START ------------------- ");
        System.out.println();
        System.out.println("findByCountry: " + regionRepository.findByCountry("Canada"));
        System.out.println("findDistinctByCountry: " + regionRepository.findDistinctByCountry("Canada"));
        System.out.println("findByCountryContaining: " + regionRepository.findByCountryContaining("United"));
        System.out.println("findByCountryContainingOrderByCountry: " + regionRepository.findByCountryContainingOrderByCountry("Asia"));
        System.out.println("findTop2ByCountry: " + regionRepository.findTop2ByCountry("Canada"));
        System.out.println();
        System.out.println(" ------------------- REGION END ------------------- ");

        System.out.println();
        System.out.println(" ------------------- DEPARTMENT START ------------------- ");
        System.out.println();
        System.out.println("findByDepartment = " + departmentRepository.findByDepartment("Toys"));
        System.out.println("findByDivisionEquals = " + departmentRepository.findByDivisionIs("Outdoors"));
        System.out.println("findDistinctTop3ByDivisionContains = " + departmentRepository.findDistinctTop3ByDivisionContains("Hea"));
        System.out.println();
        System.out.println(" ------------------- DEPARTMENT END ------------------- ");

        System.out.println();
        System.out.println(" ------------------- EMPLOYEE START ------------------- ");
        System.out.println();
        System.out.println("findEmployeesBy HireDate: " + employeeRepository.findByHireDateBetween(LocalDate.parse("2007-02-06"), LocalDate.parse("2008-09-27")));
        System.out.println("findEmployeeDetail: " + employeeRepository.findEmployeeDetail());
        System.out.println("getEmployeeSalaryByEmail: " + employeeRepository.getEmployeeSalaryByEmail());
        //POSITIONAL QUERY
        System.out.println("getEmployeeDetail by email/salary: " + employeeRepository.getEmployeeDetail("tboncoeurr8@ucla.edu", 34565));
        System.out.println("getEmployeeDetail by email: " + employeeRepository.getEmployeeDetail("dambrogir6@diigo.com"));
        System.out.println();
        System.out.println(" ------------------- EMPLOYEE END ------------------- ");

        System.out.println();
        System.out.println(" ------------------- COURSE START ------------------- ");
        System.out.println();

        System.out.println();
        System.out.println(" ------------------- COURSE END ------------------- ");

    }
}
