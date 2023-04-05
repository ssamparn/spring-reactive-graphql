package com.reactive.graphql.microservices.graphqlplayground.customer.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlplayground.customer.model.Customer;
import com.reactive.graphql.microservices.graphqlplayground.customer.service.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @QueryMapping(name = "customers")
    public Flux<Customer> allCustomers() {
        return customerService.allCustomers();
    }

    @QueryMapping(name = "customerById")
    public Mono<Customer> customerById(@Argument(name = "customerId") Integer customerId) {
        return customerService.customerById(customerId);
    }

    @QueryMapping(name = "customerNameContains")
    public Flux<Customer> customerNameContains(@Argument(name = "customerName") String customerName) {
        return customerService.customerNameContains(customerName);
    }
}
