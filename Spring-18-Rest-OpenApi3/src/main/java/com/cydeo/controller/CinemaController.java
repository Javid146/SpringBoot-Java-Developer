package com.cydeo.controller;

import com.cydeo.entity.Cinema;
import com.cydeo.entity.User;
import com.cydeo.repository.CinemaRepository;
import com.cydeo.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
//Tag create all controller methods in this class with cinema, instead of tagging each method with Cinema tag like in yaml file
//and all Cinema related method will be under cinema in swagger
@Tag(name = "Cinema",description = "Cinema CRUD Operations")
public class CinemaController {

    private final CinemaRepository cinemaRepository;

    public CinemaController(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @GetMapping("/cinemas")
    @Operation(summary = "Read all cinemas") //will show summary in swagger as "Read all cinemas"

    private List<Cinema> readAllCinemas(){
        return cinemaRepository.findAll();
    }
}

//todo openApi dependency is needed if we want to use swagger