package com.reactive.graphql.microservices.graphqlmovieapp.client;

import lombok.extern.slf4j.Slf4j;

import com.reactive.graphql.microservices.graphqlmovieapp.model.Review;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
public class ReviewRestClient {

    private final WebClient webClient;

    public ReviewRestClient(@Value("${review.service.url}") String reviewUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(reviewUrl)
                .defaultHeaders(headers -> headers.add("Content-Type", APPLICATION_JSON_VALUE))
                .build();
    }

    public Flux<Review> movieReviews(Integer movieId) {
        log.info("reviewService : movieReviews : for movieId: {}", movieId);
        return this.webClient.get()
                .uri("/{movieId}", movieId)
                .retrieve()
                .bodyToFlux(Review.class);
    }
}
