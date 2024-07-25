package com.reactive.graphql.microservices.graphqlmovieapp.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlmovieapp.client.CustomerRestClient;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Customer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRestClient customerRestClient;

    @QueryMapping
    public Mono<Customer> userProfile(@Argument("id") Integer id) {
        return customerRestClient.customerDetails(id);
    }
}
