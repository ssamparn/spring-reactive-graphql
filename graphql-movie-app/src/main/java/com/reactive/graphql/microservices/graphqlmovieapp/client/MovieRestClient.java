package com.reactive.graphql.microservices.graphqlmovieapp.client;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.reactive.graphql.microservices.graphqlmovieapp.model.Genre;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
public class MovieRestClient {

    private final WebClient webClient;

    public MovieRestClient(@Value("${movie.service.url}") String movieUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(movieUrl)
                .defaultHeaders(headers -> headers.add("Content-Type", APPLICATION_JSON_VALUE))
                .build();
    }

    public Flux<Movie> moviesByIds(List<Integer> movieIds) {
        log.info("movie service : getMoviesByIds: {}", movieIds);
        return movieIds.isEmpty() ? Flux.empty() : getMoviesByIds(movieIds);
    }

    private Flux<Movie> getMoviesByIds(List<Integer> movieIds) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("ids", movieIds).build())
                .retrieve()
                .bodyToFlux(Movie.class);
    }

    public Flux<Movie> moviesByGenre(Genre movieGenre) {
        log.info("movie service : getMoviesByGenre: {}", movieGenre);
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{genre}/recommended").build(movieGenre))
                .retrieve()
                .bodyToFlux(Movie.class);
    }
}
