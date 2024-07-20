package com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var isEmpty = exchange.getRequest().getHeaders().getOrEmpty("x-request-id").isEmpty();
        return !isEmpty ? chain.filter(exchange) :
                Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST));
    }
}
