package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.serverapp.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerDto;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerNotFound;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.DeleteResponse;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.serverapp.service.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @QueryMapping
    public Flux<CustomerDto> customers() {
        return this.customerService.allCustomers();
    }

//    @QueryMapping
//    public Mono<CustomerDto> customerById(@Argument Integer id) {
//        return this.customerService.customerById(id)
//                .switchIfEmpty(Mono.error(new RuntimeException("no such user found")));
//    }

    @QueryMapping
    public Mono<Object> customerById(@Argument Integer id) {
        return this.customerService.customerById(id)
                .cast(Object.class)
                .switchIfEmpty(Mono.just(CustomerNotFound.create(id, "Customer not found")));
    }

    @MutationMapping
    public Mono<CustomerDto> createCustomer(@Argument("customer") CustomerDto customer) {
        return this.customerService.createCustomer(customer);
    }

    @MutationMapping
    public Mono<CustomerDto> updateCustomer(@Argument Integer id, @Argument("customer") CustomerDto customer) {
        return this.customerService.updateCustomer(id, customer);
    }

    @MutationMapping
    public Mono<DeleteResponse> deleteCustomer(@Argument Integer id) {
        return this.customerService.deleteCustomer(id);
    }

}
