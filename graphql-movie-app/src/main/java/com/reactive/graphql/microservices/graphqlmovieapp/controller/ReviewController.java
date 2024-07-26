package com.reactive.graphql.microservices.graphqlmovieapp.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlmovieapp.client.ReviewRestClient;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Movie;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Review;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRestClient reviewRestClient;

    @SchemaMapping(typeName = "MovieDetails")
    public Flux<Review> reviews(Movie movie) {
        return this.reviewRestClient.movieReviews(movie.getId());
    }
}
