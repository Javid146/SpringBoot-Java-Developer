package com.cydeo.controller;

import com.cydeo.entity.Genre;
import com.cydeo.entity.MovieCinema;
import com.cydeo.repository.GenreRepository;
import com.cydeo.repository.MovieCinemaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class Consume_WebClient {

    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    private final MovieCinemaRepository movieCinemaRepository;
    private final GenreRepository genreRepository;

    public Consume_WebClient(MovieCinemaRepository movieCinemaRepository, GenreRepository genreRepository) {
        this.movieCinemaRepository = movieCinemaRepository;
        this.genreRepository = genreRepository;
    }

    //flux is like list, but has no blocking structure. this makes our api reactive
    //which means it returns more than 1 without waiting for other actions to be done
    //this is asynchronous way. java is usually synchronous way. let's say there is a line in cafe, customers are served 1 by 1
    //asynchronous is that several people orders pizza, dominos cooks all, but does not wait to serve 1st customer, whoever's pizza is ready 1st, that customer gets it
    //it is like multi-threading. actions are not done by line. many actions can be taken by multiple threads
    @GetMapping("/flux-movie-cinemas") // GET localhost:8080/flux-movie-cinemas
    public Flux<MovieCinema> readAllCinemaFlux(){
        return Flux.fromIterable(movieCinemaRepository.findAll());
    }

    //flux returns many like list, mono returns just 1 piece of data
//    @GetMapping("/mono-movie-cinema/{id}") // GET localhost:8080/mono-movie-cinema/2
//    public Mono<MovieCinema> readById(@PathVariable("id") Long id){
//        return Mono.just(movieCinemaRepository.findById(id).get());
//    }

    //similar call as above mono, but inside of ResponseEntity. will fetch data
    @GetMapping("/mono-movie-cinema/{id}")
    public ResponseEntity<Mono<MovieCinema>> readById(@PathVariable("id") Long id){
        return ResponseEntity.ok(Mono.just(movieCinemaRepository.findById(id).get()));
    }

    //we create new single piece of data with mono and pass RequestBody in it. should see it in db after run
    @PostMapping("/create-genre")
    public Mono<Genre> createGenre(@RequestBody Genre genre){
        Genre createGenre = genreRepository.save(genre);
        return Mono.just(createGenre);
    }

    //delete 1 piece of data with mono
    @DeleteMapping("delete/genre/{id}")
    public Mono<Void> deleteGenre(@PathVariable("id") Long id){
        genreRepository.deleteById(id);
        return Mono.empty();
    }


    // todo -----------------------WEBCLIENT--------------------------------------------------------------------

    //consume API we created with WEBCLIENT. flux will consume more than 1 object
    @GetMapping("/flux") //1. we will run http://localhost:8080/flux, api we created above
    public Flux<MovieCinema> readWitWebClient(){

        return webClient
                .get()
                .uri("/flux-movie-cinemas")  //2. and it will consume api coming from http://localhost:8080/flux-movie-cinemas
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(MovieCinema.class); //retrieve and map to MovieCinema.class
    }

    //consume API we created with WEBCLIENT. Mono will consume 1 object
    @GetMapping("/mono/{id}") //1. we will run http://localhost:8080/mono/{id}, api we created above
    public Mono<MovieCinema> readMonoWithWebClient(@PathVariable("id") Long id){

        return webClient
                .get()
                .uri("/mono-movie-cinema/{id}",id) //2. and it will consume api coming from http://localhost:8080/mono-movie-cinema/{id}
                .retrieve()
                .bodyToMono(MovieCinema.class);
    }
}
