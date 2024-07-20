package com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.controller.exceptionresolver;

import java.util.Collections;
import java.util.List;

import com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.exception.ApplicationException;
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
        ApplicationException applicationException = toApplicationException(exception);
        return Mono.just(
              List.of(GraphQLError.newError()
                      .errorType(applicationException.getErrorType())
                      .message(applicationException.getMessage())
                      .path(environment.getExecutionStepInfo().getPath())
                      .location(environment.getField().getSourceLocation())
                              .extensions(applicationException.getExtensions())
                      .build())
        );
    }

    private ApplicationException toApplicationException(Throwable exception) {
        return ApplicationException.class.equals(exception.getClass()) ?
                (ApplicationException) exception : new ApplicationException(ErrorType.INTERNAL_ERROR, exception.getMessage(), Collections.emptyMap());
    }
}
