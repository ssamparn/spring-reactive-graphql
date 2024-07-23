package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.clientapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.clientapp.client.CustomerClient;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerDto;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerNotFound;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerResponse;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.GenericResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.graphql.ResponseError;
import org.springframework.graphql.client.ClientResponseField;
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
                .then(getCustomerById()) // run either getCustomerById() or getCustomerByIdWithUnion()
//                .then(getCustomerByIdWithUnion())
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
        Mono<GenericResponse<CustomerDto>> customerMono = customerClient.queryWithDocumentAndVariable("get-customer-by-id", Map.of("id", 1))
                .map(clientResponse -> {
                    ClientResponseField responseField = clientResponse.field("customerById");
                    return responseField.getValue() != null ?
                            new GenericResponse<>(responseField.toEntity(CustomerDto.class)) :
                            new GenericResponse<>(responseField.getErrors()
                                    .stream()
                                    .map(ResponseError::getMessage)
                                    .collect(Collectors.joining(";"))
                            );
                });

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> log.info("Executing raw query ...."))
                .then(customerMono)
                .doOnNext(customer -> log.info("Customer with id: 1: {}", customer))
                .then();
    }

    private Mono<Void> getCustomerByIdWithUnion() {
        Mono<Object> unionMono = customerClient.queryWithDocumentAndVariable("get-customer-by-id-with-type-name", Map.of("id", 1))
                .map(clientResponse -> {
                    ClientResponseField responseField = clientResponse.field("customerById");
                    boolean isCustomer = "Customer".equals(clientResponse.field("customerById.type").getValue());
                    return isCustomer ? responseField.toEntity(CustomerDto.class) : responseField.toEntity(CustomerNotFound.class);
                });

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> log.info("Executing raw query ...."))
                .then(unionMono)
                .doOnNext(customer -> log.info("Customer with id: {}", customer))
                .then();
    }

}
