package com.cydeo.controller;

import com.cydeo.client.EmployeeClient;
import com.cydeo.client.UserClient;
import com.cydeo.dto.Employee;
import com.cydeo.dto.ResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Consume_FeignClient {

    private final UserClient userClient;
    private final EmployeeClient employeeClient;

    public Consume_FeignClient(UserClient userClient, EmployeeClient employeeClient) {
        this.userClient = userClient;
        this.employeeClient = employeeClient;
    }

    //we have endpoint but no URI here, because it comes with userClient.getUsers() from UserClient interface (which is feignClient)
    //so we can verify it with: localhost:8080/api/v1/users
    @GetMapping("/api/v1/users")
    public ResponseEntity<ResponseWrapper> getUsers(){

        //we consume API with feign client and return it as our custom class (ResponseWrapper)
        return ResponseEntity.ok(new ResponseWrapper("UserList Retrieved",userClient.getUsers()));
    }

    @GetMapping("/api/v1/employee")
    public ResponseEntity<ResponseWrapper> getEmployees(){                                                          //requires as in header to get response. "app-id":"6298ebfecd0551211fce37a6"
        return ResponseEntity.ok( new ResponseWrapper("Successfully retrieved ",employeeClient.getEmployee("6298ebfecd0551211fce37a6")));
    }
}
