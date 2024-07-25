package com.reactive.graphql.microservices.graphqlmovieapp.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlmovieapp.client.ReviewRestClient;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRestClient reviewRestClient;
}
