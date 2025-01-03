package com.cydeo.client;

import com.cydeo.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//FeignClient is to consume api, comes from Spring cloud and needs dependency. we need to add EnableFeignClients in runner class
//gets external URI uses in interface. Here to fetch all users. We use it in Consume_FeignClient
//so FeignClient goes to https://jsonplaceholder.typicode.com/users, returns users and uses for our endpoint
@FeignClient(url = "https://jsonplaceholder.typicode.com",name = "USER-CLIENT")
public interface UserClient {

    @GetMapping("/users")
    List<User> getUsers();




}
