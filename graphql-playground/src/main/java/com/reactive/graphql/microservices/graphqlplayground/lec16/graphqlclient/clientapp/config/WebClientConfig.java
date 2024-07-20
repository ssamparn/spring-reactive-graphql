package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.clientapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(@Value("${customer.service.url}") String customerUrl) {
        return WebClient.builder()
                .baseUrl(customerUrl)
                .build();
    }
}
