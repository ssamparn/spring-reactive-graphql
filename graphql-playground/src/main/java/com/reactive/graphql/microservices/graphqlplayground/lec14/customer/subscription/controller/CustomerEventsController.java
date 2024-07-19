package com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.model.CustomerEvent;
import com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.service.CustomerEventService;
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