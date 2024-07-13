package com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.model.Customer;
import com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.service.CustomerService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @QueryMapping(name = "customers")
    public Flux<Customer> allCustomers() {
        return customerService.allCustomers();
    }
}