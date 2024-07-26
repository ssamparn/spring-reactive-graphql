package com.reactive.graphql.microservices.graphqlmovieapp.client;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.reactive.graphql.microservices.graphqlmovieapp.dto.CustomerInput;
import com.reactive.graphql.microservices.graphqlmovieapp.dto.WatchListInput;
import com.reactive.graphql.microservices.graphqlmovieapp.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
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
        log.info("customer service : getCustomerDetails : for customerId: {}", customerId);
        return this.webClient.get()
                .uri("/{id}", customerId)
                .retrieve()
                .bodyToMono(Customer.class);
    }

    public Mono<Customer> updateCustomerDetails(CustomerInput request) {
        log.info("customer service : updateCustomerDetails : for customerId: {}", request.getId());
        return this.webClient.put()
                .uri("/{id}", request.getId())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Customer.class);
    }

    public Mono<List<Integer>> addToWatchList(WatchListInput watchList) {
        log.info("customer service : addMovieToWatchList : for customerId: {} : for movieId: {}", watchList.getCustomerId(), watchList.getMovieId());
        return this.webClient.post()
                .uri("/watchlist")
                .bodyValue(watchList)
                .retrieve()
                .bodyToFlux(Integer.class)
                .collectList();
    }
}
