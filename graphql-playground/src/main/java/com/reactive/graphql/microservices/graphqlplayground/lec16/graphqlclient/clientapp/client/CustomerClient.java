package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.clientapp.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class CustomerClient {

    private final WebClient webClient;
    private final HttpGraphQlClient graphQlClient;

    public CustomerClient(@Value("${customer.service.url}") String customerUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(customerUrl)
                .defaultHeaders(headers -> headers.add("Content-Type", APPLICATION_JSON_VALUE))
                .build();
        this.graphQlClient = HttpGraphQlClient.builder(webClient).build();
    }

    public Mono<ClientGraphQlResponse> graphQl(String queryString) {
        return graphQlClient
                .document(queryString)
                .execute();
    }

    public Mono<ClientGraphQlResponse> graphQl(String documentName, String operationName, Map<String, Object> variables) {
        return graphQlClient
                .documentName(documentName)
                .operationName(operationName)
                .variables(variables)
                .execute();
    }
}
