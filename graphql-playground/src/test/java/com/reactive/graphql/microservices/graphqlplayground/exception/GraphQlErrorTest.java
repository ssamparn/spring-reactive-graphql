package com.reactive.graphql.microservices.graphqlplayground.exception;

import java.util.UUID;

import com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.model.CustomerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

// Run this test while only enabling package lec15
@Disabled
@SpringBootTest
@AutoConfigureHttpGraphQlTester
public class GraphQlErrorTest {

    @Autowired
    private HttpGraphQlTester graphQlTestClient;

    @Test
    public void createCustomerTest(){
        this.graphQlTestClient
                .mutate().header("x-request-id", UUID.randomUUID().toString()).build()
                .documentName("crud-operations")
                .variable("customer", CustomerDto.create(null, "michael", 15, "seattle"))
                .operationName("CreateNewCustomer")
                .execute()
                .errors().satisfy(list -> {
                    Assertions.assertThat(list).hasSize(1);
                    Assertions.assertThat(list.get(0).getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
                })
                .path("response").valueIsNull();
    }
}
