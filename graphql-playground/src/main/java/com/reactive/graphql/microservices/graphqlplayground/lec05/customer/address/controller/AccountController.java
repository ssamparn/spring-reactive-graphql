package com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.model.Account;
import com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.model.Account.AccountType;
import com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.model.Customer;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class AccountController {

    @SchemaMapping(typeName = "Customer")
    public Mono<Account> account(Customer customer, DataFetchingFieldSelectionSet fields, DataFetchingEnvironment environment) {
        log.info("Account fields: {}", fields.getFields());
        log.info("Account environments: {}", environment.getDocument());
        log.info("Account operation name: {}", environment.getOperationDefinition());
        var type = ThreadLocalRandom.current().nextBoolean() ? AccountType.CURRENT : AccountType.SAVINGS;
        return Mono.just(
                Account.create(
                        UUID.randomUUID(),
                        ThreadLocalRandom.current().nextInt(1, 1000),
                        type)
        );
    }
}