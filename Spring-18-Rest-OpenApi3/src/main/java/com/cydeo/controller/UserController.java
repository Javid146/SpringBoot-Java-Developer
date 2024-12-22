package com.cydeo.controller;

import com.cydeo.entity.User;
import com.cydeo.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
//Tag create all controller methods in this class with User, instead of tagging each method with User tag like in yaml file
//and all User related method will be under cinema in swagger
@Tag(name = "User",description = "User CRUD Operations")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    @Operation(summary = "Read all users") //will show summary in swagger as "Read all users"
    @ApiResponses(value = { //parameters for swagger. go to: http://localhost:8080/swagger-custom.html
            @ApiResponse(responseCode = "200",description = "Successfully retrieved users (OK)", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",description = "Something went wrong",content = @Content),
            @ApiResponse(responseCode = "404",description = "Not Found",content = @Content)
    })
    public List<User> readAllUsers() {
        return userRepository.findAll();
    }
}

//todo openApi dependency is needed if we want to use swagger