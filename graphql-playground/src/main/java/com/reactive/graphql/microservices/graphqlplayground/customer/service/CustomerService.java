package com.reactive.graphql.microservices.graphqlplayground.customer.service;

import com.reactive.graphql.microservices.graphqlplayground.customer.model.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final Flux<Customer> customerFlux = Flux.just(
            Customer.create(1, "Sam", 20, "atlanta"),
            Customer.create(2, "Jake", 10, "Las Vegas"),
            Customer.create(3, "Mike", 15, "Miami"),
            Customer.create(4, "John", 5, "Houston")
    );

    public Flux<Customer> allCustomers() {
        return this.customerFlux;
    }

    public Mono<Customer> customerById(Integer customerId) {
        return customerFlux.filter(customer -> customer.getId().equals(customerId))
                .next();
    }

    public Flux<Customer> customerNameContains(String customerName) {
        return customerFlux.filter(customer -> customer.getName().contains(customerName));
    }
}
