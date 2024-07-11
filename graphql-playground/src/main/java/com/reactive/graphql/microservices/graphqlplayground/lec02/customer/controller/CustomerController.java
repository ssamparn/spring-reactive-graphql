package com.reactive.graphql.microservices.graphqlplayground.lec02.customer.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlplayground.lec02.customer.model.AgeRangeFilter;
import com.reactive.graphql.microservices.graphqlplayground.lec02.customer.model.Customer;
import com.reactive.graphql.microservices.graphqlplayground.lec02.customer.service.CustomerService;
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

    @QueryMapping(name = "customersByAgeRange")
    public Flux<Customer> customersByAgeRange(@Argument(name = "filter") AgeRangeFilter ageRangeFilter) {
        // Argument name must be filter or rename ageRangeFilter to filter for it to work implicitly.
        return customerService.withinAge(ageRangeFilter.getMinAge(), ageRangeFilter.getMaxAge());
    }
}
