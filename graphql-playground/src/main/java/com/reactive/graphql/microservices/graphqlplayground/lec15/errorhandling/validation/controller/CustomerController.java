package com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.exception.ApplicationErrors;
import com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.model.CustomerDto;
import com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.model.DeleteResponse;
import com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.service.CustomerService;
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

    @QueryMapping
    public Mono<CustomerDto> customerById(@Argument Integer id) {
        return this.customerService.customerById(id)
                .switchIfEmpty(ApplicationErrors.noSuchUser(id));
    }

//    @QueryMapping
//    public Mono<Object> customerById(@Argument Integer id) {
//        return this.customerService.customerById(id)
//                .cast(Object.class)
//                .switchIfEmpty(Mono.just(CustomerNotFound.create(id, "Customer not found")));
//    }

    @MutationMapping
    public Mono<CustomerDto> createCustomer(@Argument("customer") CustomerDto customer) {
        return Mono.just(customer)
                .filter(c -> c.getAge() >= 18)
                .flatMap(this.customerService::createCustomer)
                .switchIfEmpty(ApplicationErrors.mustBe18(customer));
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