package com.reactive.graphql.microservices.graphqlplayground.lec07.customer.order.config;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlplayground.lec07.customer.order.service.CustomerOrderDataFetcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
@RequiredArgsConstructor
public class DataFetcherWiringConfig {

    private final CustomerOrderDataFetcher dataFetcher;

    @Bean
    public RuntimeWiringConfigurer configurer() {
        return c -> c.type("Query", b -> b.dataFetcher("customers", dataFetcher));
    }
}
