package com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.service;

import com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.model.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {

    private final Flux<Customer> customerFlux = Flux.just(
            Customer.create(1, "Sam", 20, "Atlanta"),
            Customer.create(2, "Jake", 10, "Las Vegas"),
            Customer.create(3, "Mike", 15, "Miami"),
            Customer.create(4, "John", 5, "Houston"),
            Customer.create(5, "Theta", 25, "Texas"),
            Customer.create(6, "Beta", 30, "Harlem")
    );

    public Flux<Customer> allCustomers() {
        return this.customerFlux;
    }

}
