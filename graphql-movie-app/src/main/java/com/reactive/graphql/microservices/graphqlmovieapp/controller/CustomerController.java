package com.reactive.graphql.microservices.graphqlmovieapp.controller;

import lombok.RequiredArgsConstructor;

import java.util.Collections;

import com.reactive.graphql.microservices.graphqlmovieapp.client.CustomerRestClient;
import com.reactive.graphql.microservices.graphqlmovieapp.dto.CustomerInput;
import com.reactive.graphql.microservices.graphqlmovieapp.dto.WatchListInput;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Customer;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Status;
import com.reactive.graphql.microservices.graphqlmovieapp.model.WatchListResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRestClient customerRestClient;

    @QueryMapping
    public Mono<Customer> userProfile(@Argument("id") Integer id) {
        return this.customerRestClient.customerDetails(id);
    }

    @MutationMapping
    public Mono<WatchListResponse> addToWatchlist(@Argument("input") WatchListInput watchListInput) {
        return this.customerRestClient.addToWatchList(watchListInput)
                .map(watchLists -> WatchListResponse.create(Status.SUCCESS, watchLists))
                .onErrorReturn(WatchListResponse.create(Status.FAILURE, Collections.emptyList()));
    }

    @MutationMapping
    public Mono<Customer> updateUserProfile(@Argument("input") CustomerInput customerInput) {
        return this.customerRestClient.updateCustomerDetails(customerInput);
    }
}
