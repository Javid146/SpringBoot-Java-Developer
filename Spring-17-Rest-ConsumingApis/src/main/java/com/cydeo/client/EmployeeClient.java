package com.cydeo.client;

import com.cydeo.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//FeignClient is to consume api, comes from Spring cloud and needs dependency. we need to add EnableFeignClients in runner class
//gets external URI uses in interface. Here to fetch user.We use it in Consume_FeignClient
//so FeignClient goes to https://dummyapi.io/data/v1/user?limit=10, returns data and uses for our endpoint
@FeignClient(url = "https://dummyapi.io",name = "DUMMY-API")
public interface EmployeeClient {

    //https://dummyapi.io/data/v1/user?limit=10

    //@RequestHeader means header we need to input in header to fetch external api response
    //for postman you need to add that header to see response
    @GetMapping("/data/v1/user?limit=10")
    Employee getEmployee(@RequestHeader("app-id") String id);

    //Employee class was create in https://www.jsonschema2pojo.org/ site. you paste any json response u want to use
    //add current package name (com.cydeo.client),DTO class name you want to create
    //source type:JSON, annotation type:jackson
    //check: allow additional properties, include getters/setter, use double numbers
    //and zip it.
    //then copy created classes and add to dto package.
}
