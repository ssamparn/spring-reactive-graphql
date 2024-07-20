package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.serverapp.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerEvent;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.serverapp.service.CustomerEventService;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class CustomerEventsController {

    private final CustomerEventService customerEventService;

    @SubscriptionMapping
    public Flux<CustomerEvent> customerEvents() {
        return this.customerEventService.subscribe();
    }
}