package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.clientapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.clientapp.client.CustomerClient;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerDto;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerNotFound;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.DeleteResponse;
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
                .then(this.createNewCustomer())
                .then(this.getAllCustomers())
                .then(this.updateCustomer())
                .then(this.getAllCustomersWithDocumentName())
                .then(this.getCustomerById())
                .then(this.deleteCustomer())
                .then(this.getAllCustomers())
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

        Mono<List<CustomerDto>> customerList = customerClient.graphQl(queryString)
                .map(response -> response.field("customers"))
                .map(field -> field.toEntityList(CustomerDto.class));

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> log.info("Executing raw query ...."))
                .then(customerList)
                .doOnNext(customer -> log.info("All customer details: {}", customer))
                .then();
    }

    private Mono<Void> getAllCustomersWithDocumentName() {
        Mono<List<CustomerDto>> customerList = customerClient.graphQl("get-all-customers", "GetAllCustomers", Collections.emptyMap())
                .map(response -> response.field("customers"))
                .map(field -> field.toEntityList(CustomerDto.class));

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> log.info("Executing raw query ...."))
                .then(customerList)
                .doOnNext(customer -> log.info("All customer details: {}", customer))
                .then();
    }

    private Mono<Void> getCustomerById() {
        Mono<GenericResponse<CustomerDto>> customerMono = customerClient.graphQl("get-customer-by-id", "CustomerById", Map.of("id", 5))
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
                .doOnNext(customer -> log.info("Customer with id: 5 {}", customer))
                .then();
    }

    public Mono<Void> createNewCustomer() {
        Mono<CustomerDto> newCustomerResponseMono = customerClient.graphQl("crud-operations", "CreateNewCustomer", Map.of("customer", CustomerDto.create(null, "obie", 45, "detroit")))
                .map(response -> response.field("response"))
                .mapNotNull(field -> field.toEntity(CustomerDto.class));

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> log.info("Executing raw query ...."))
                .then(newCustomerResponseMono)
                .doOnNext(customer -> log.info("New Customer Details: {}", customer))
                .then();
    }

    public Mono<Void> updateCustomer() {
        Mono<CustomerDto> customerResponseMono = customerClient.graphQl("crud-operations", "UpdateCustomer", Map.of("id", 5,"customer", CustomerDto.create(null, "jackson", 46, "dallas")))
                .map(response -> response.field("response"))
                .mapNotNull(field -> field.toEntity(CustomerDto.class));

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> log.info("Executing raw query ...."))
                .then(customerResponseMono)
                .doOnNext(customer -> log.info("Updated Customer Details: {}", customer))
                .then();
    }

    public Mono<Void> deleteCustomer() {
        Mono<DeleteResponse> customerResponseMono = customerClient.graphQl("crud-operations", "DeleteCustomer", Map.of("id", 3))
                .map(response -> response.field("response"))
                .mapNotNull(field -> field.toEntity(DeleteResponse.class));

        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> log.info("Executing raw query ...."))
                .then(customerResponseMono)
                .doOnNext(customer -> log.info("Deleted Customer Details: {}", customer))
                .then();
    }

    // run either getCustomerById() or getCustomerByIdWithUnion()
    private Mono<Void> getCustomerByIdWithUnion() {
        Mono<Object> unionMono = customerClient.graphQl("get-customer-by-id-with-type-name", "CustomerById", Map.of("id", 1))
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
