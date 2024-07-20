package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.clientapp.client;

import java.util.Map;

import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CustomerClient {

    private final WebClient webClient;
    private final HttpGraphQlClient graphQlClient;

    public CustomerClient(WebClient webClient) {
        this.webClient = webClient;
        this.graphQlClient = HttpGraphQlClient.builder(webClient).build();
    }

    public Mono<ClientGraphQlResponse> query(String queryString) {
        return graphQlClient.document(queryString)
                .execute();
    }

    public Mono<ClientGraphQlResponse> queryWithDocument(String documentName) {
        return graphQlClient.documentName(documentName)
                .execute();
    }

    public Mono<ClientGraphQlResponse> queryWithDocumentAndVariable(String documentName, Map<String, Object> variables) {
        return graphQlClient.documentName(documentName)
                .variables(variables)
                .execute();
    }
}
