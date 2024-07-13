package com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.controller;

import lombok.extern.slf4j.Slf4j;

import com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.model.Address;
import com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.model.Customer;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class AddressController {

    @SchemaMapping(typeName = "Customer")
    public Mono<Address> address(Customer customer, DataFetchingFieldSelectionSet fields, DataFetchingEnvironment environment) {
        log.info("Address fields: {}", fields.getFields());
        log.info("Address environments: {}", environment.getDocument());
        log.info("Address operation name: {}", environment.getOperationDefinition());
        return Mono.just(
                Address.create(
                        customer.getName() + " street",
                        customer.getName() + " city" )
        );
    }
}