package com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.exception;

import java.time.LocalDateTime;
import java.util.Map;

import com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.model.CustomerDto;
import org.springframework.graphql.execution.ErrorType;
import reactor.core.publisher.Mono;

public class ApplicationErrors {

    public static <T> Mono<T> noSuchUser(Integer userId) {
        return Mono.error(
                new ApplicationException(ErrorType.NOT_FOUND, "No such user found", Map.of("customerId", userId, "timeStamp", LocalDateTime.now()))
        );
    }

    public static <T> Mono<T> mustBe18(CustomerDto customer) {
        return Mono.error(
                new ApplicationException(ErrorType.BAD_REQUEST, "Must be 18 or above", Map.of("customer", customer, "timeStamp", LocalDateTime.now()))
        );
    }
}