package com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.interceptor;

import java.util.Map;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RequestInterceptor implements WebGraphQlInterceptor {

    @Override // client has to pass x-request-id in its request header
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        var headers = request.getHeaders().getOrEmpty("x-request-id");
        var xRequestId = headers.isEmpty() ? "" : headers.getFirst();
        request.configureExecutionInput((e, b) -> b.graphQLContext(Map.of("x-request-id", xRequestId)).build());
        return chain.next(request);
    }
}
