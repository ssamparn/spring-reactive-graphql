package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.clientapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.clientapp.client.CustomerClient;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerClientService implements CommandLineRunner {

    private final CustomerClient customerClient;

    @Override
    public void run(String... args) {
        getAllCustomers()
                .then(getAllCustomersWithDocumentName())
                .then(getCustomerById())
                .subscribe();
    }

    private Mono<Void> getAllCustomers() {
        String queryString = """
            query GetAllCustomers {
              customers {
                id
                name
                age
                city
             }
            }""";

        Mono<List<CustomerDto>> customerList = customerClient.query(queryString)
                .map(response -> response.field("customers"))
                .map(field -> field.toEntityList(CustomerDto.class));

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> log.info("Executing raw query ...."))
                .then(customerList)
                .doOnNext(customer -> log.info("Customer details: {}", customer))
                .then();
    }

    private Mono<Void> getAllCustomersWithDocumentName() {
        Mono<List<CustomerDto>> customerList = customerClient.queryWithDocument("get-all-customers")
                .map(response -> response.field("customers"))
                .map(field -> field.toEntityList(CustomerDto.class));

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> log.info("Executing raw query ...."))
                .then(customerList)
                .doOnNext(customer -> log.info("Customer details: {}", customer))
                .then();
    }

    private Mono<Void> getCustomerById() {
        Mono<CustomerDto> customerMono = customerClient.queryWithDocumentAndVariable("get-customer-by-id", Map.of("id", 1))
                .map(response -> response.field("customerById"))
                .mapNotNull(field -> field.toEntity(CustomerDto.class));

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> log.info("Executing raw query ...."))
                .then(customerMono)
                .doOnNext(customer -> log.info("Customer with id: 1: {}", customer))
                .then();
    }
}
