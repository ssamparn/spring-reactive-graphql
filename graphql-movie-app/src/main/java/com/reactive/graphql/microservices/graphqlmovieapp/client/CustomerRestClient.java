package com.reactive.graphql.microservices.graphqlmovieapp.client;

import java.util.List;

import com.reactive.graphql.microservices.graphqlmovieapp.dto.CustomerInput;
import com.reactive.graphql.microservices.graphqlmovieapp.dto.WatchListInput;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class CustomerRestClient {

    private final WebClient webClient;

    public CustomerRestClient(@Value("${customer.service.url}") String customerUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(customerUrl)
                .defaultHeaders(headers -> headers.add("Content-Type", APPLICATION_JSON_VALUE))
                .build();
    }

    public Mono<Customer> customerDetails(Integer customerId) {
        return this.webClient.get()
                .uri("{id}", customerId)
                .retrieve()
                .bodyToMono(Customer.class);
    }

    public Mono<Customer> updateCustomerDetails(CustomerInput request) {
        return this.webClient.put()
                .uri("{id}", request.getId())
                .body(request, CustomerInput.class)
                .retrieve()
                .bodyToMono(Customer.class);
    }

    public Mono<List<Integer>> addToWatchList(WatchListInput watchList) {
        return this.webClient.post()
                .uri("watchlist")
                .body(watchList, WatchListInput.class)
                .retrieve()
                .bodyToFlux(Integer.class)
                .collectList();
    }

}
