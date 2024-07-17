package com.reactive.graphql.microservices.graphqlplayground.lec12.operationcaching;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import graphql.ExecutionInput;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.execution.preparsed.PreparsedDocumentProvider;

@Slf4j
@Configuration
public class OperationCachingConfig {

    /* *
     * request body
     * exec doc
     * parse
     * validation
     * exec doc

     * suggestion:
     *   use variables along with operation mame
     *   prefer this: https://github.com/ben-manes/caffeine
     * */

    @Bean
    public GraphQlSourceBuilderCustomizer sourceBuilderCustomizer(PreparsedDocumentProvider provider){
        return c -> c.configureGraphQl(builder -> builder.preparsedDocumentProvider(provider));
    }

    @Bean
    public PreparsedDocumentProvider provider() {
        Map<String, CompletableFuture<PreparsedDocumentEntry>> map = new ConcurrentHashMap<>();
        return new PreparsedDocumentProvider() {
            @Override
            public CompletableFuture<PreparsedDocumentEntry> getDocumentAsync(ExecutionInput executionInput, Function<ExecutionInput, PreparsedDocumentEntry> parseAndValidateFunction) {
                return map.computeIfAbsent(executionInput.getQuery(), key -> {
                log.info("Query not executed before : {}", key);
                PreparsedDocumentEntry preparsedDocumentEntry = parseAndValidateFunction.apply(executionInput);
                log.info("Caching document entry : {}", preparsedDocumentEntry);
                return CompletableFuture.completedFuture(preparsedDocumentEntry);
            });
            }
        };
    }
}
