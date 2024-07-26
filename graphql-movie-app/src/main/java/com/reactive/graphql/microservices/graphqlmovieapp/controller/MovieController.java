package com.reactive.graphql.microservices.graphqlmovieapp.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.reactive.graphql.microservices.graphqlmovieapp.client.MovieRestClient;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Customer;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Genre;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Movie;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieRestClient movieRestClient;

    @SchemaMapping(typeName = "UserProfile")
    public Flux<Movie> watchList(Customer customer) {
        return movieRestClient.moviesByIds(customer.getWatchList());
    }

    @SchemaMapping(typeName = "UserProfile")
    public Flux<Movie> recommended(Customer customer) {
        return movieRestClient.moviesByGenre(customer.getFavoriteGenre());
    }

    @QueryMapping
    public Mono<Movie> movieDetails(@Argument(name = "id") Integer movieId) {
        return movieRestClient.moviesByIds(List.of(movieId))
                .next();
    }

    @QueryMapping
    public Flux<Movie> moviesByGenre(@Argument(name = "genre") Genre favoutiteGenre) {
        return movieRestClient.moviesByGenre(favoutiteGenre);
    }
}
