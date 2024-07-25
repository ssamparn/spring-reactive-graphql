package com.reactive.graphql.microservices.graphqlmovieapp.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlmovieapp.client.MovieRestClient;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieRestClient movieRestClient;



}
