package com.cydeo.controller;

import com.cydeo.dto.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class Consume_RestTemplate {

    //we will consume data from this api with RestTemplate and send to our endpoint
    private final String URI = "https://jsonplaceholder.typicode.com/users";

    //we create bean of it in Spring17RestConsumingApisApplication as it is third-party class and inject here.
    //RestTemplate takes data from 3rd party API (URI:https://jsonplaceholder.typicode.com/users) and maps it to DTO. It helps you to decide what data you want to ignore, what you want to be visible and stuff
    //without RestTemplate data is directly given to DTO and hard to modify
    private final RestTemplate restTemplate;

    public Consume_RestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public User[] readAllUsers(){
        //get data from 3rd pary (URI above) and give it to my endpoint (localhost:8080/users) and map it to given DTO (User). getForEntity method requires array, because there are many Users
        //getForEntity method is for mapping purpose between DTO class and json data.
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(URI,User[].class);
        //get Users from URI (https://jsonplaceholder.typicode.com/users) response and pass to my endpoint: GET localhost:8080/users
        return responseEntity.getBody();
    }

    //get data from 3rd party URI (https://jsonplaceholder.typicode.com/users) and give it to my specified endpoint GET localhost:8080/users/{id}
    @GetMapping("{id}")
    public Object readUser(@PathVariable("id") Integer id){
        String URL = URI+"/{id}";
        return restTemplate.getForObject(URL,Object.class,id);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> consumeFromDummyApi(){

        //some 3rd p. api requires us to add header to get response, this is how we do
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("app-id","6298ebfecd0551211fce37a6");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        //exchange method gets 3rd party's URI (we want to consume), add GET m, adds entity (headers to want to input) and Onbject.class
        //when we do not map any specific class we add Object class
        ResponseEntity<Object> response = restTemplate.exchange("https://dummyapi.io/data/v1/user?limit=10", HttpMethod.GET,entity,Object.class);
        return response;
    }


}
