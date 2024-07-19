package com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.controller.exceptionresolver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import reactor.core.publisher.Mono;

@Component
public class CustomerExceptionResolver implements DataFetcherExceptionResolver {

    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        return Mono.just(
              List.of(GraphQLError.newError()
                      .errorType(ErrorType.NOT_FOUND)
                      .message(exception.getMessage())
                      .path(environment.getExecutionStepInfo().getPath())
                      .location(environment.getField().getSourceLocation())
                              .extensions(Map.of("timestamp", LocalDateTime.now()))
                      .build())
        );
    }
}
