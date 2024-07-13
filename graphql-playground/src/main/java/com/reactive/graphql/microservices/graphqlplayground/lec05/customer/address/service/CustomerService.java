package com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.service;

import com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.model.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {

    private final Flux<Customer> customerFlux = Flux.just(
            Customer.create(1, "Sam", 20),
            Customer.create(2, "Jake", 10),
            Customer.create(3, "Mike", 15),
            Customer.create(4, "John", 5),
            Customer.create(5, "Theta", 25),
            Customer.create(6, "Beta", 30)
    );

    public Flux<Customer> allCustomers() {
        return this.customerFlux;
    }
}