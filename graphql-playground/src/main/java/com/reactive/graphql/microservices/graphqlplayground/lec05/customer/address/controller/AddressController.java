package com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.controller;

import com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.model.Address;
import com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.model.Customer;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {

    @SchemaMapping(typeName = "Customer")
    public Mono<Address> address(Customer customer){
        return Mono.just(
                Address.create(
                        customer.getName() + " street",
                        customer.getName() + " city" )
        );
    }
}