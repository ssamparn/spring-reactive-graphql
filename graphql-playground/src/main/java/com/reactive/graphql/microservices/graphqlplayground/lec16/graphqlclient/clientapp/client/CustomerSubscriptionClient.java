package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.clientapp.client;

import java.time.Duration;

import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.WebSocketGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Flux;

@Component
public class CustomerSubscriptionClient {

    private final WebSocketGraphQlClient webSocketGraphQlClient;

    public CustomerSubscriptionClient(@Value("${customer.events.subscription.url}") String customerSubscriptionUrl) {
        this.webSocketGraphQlClient = WebSocketGraphQlClient
                .builder(customerSubscriptionUrl, new ReactorNettyWebSocketClient())
                .keepAlive(Duration.ofSeconds(60))
                .build();
    }

    public Flux<CustomerEvent> customerEvents() {
        String subscriptionQuery = """
                subscription {
                    customerEvents {
                        id
                        action
                    }
                }
                """;
        return webSocketGraphQlClient.document(subscriptionQuery)
                .retrieveSubscription("customerEvents")
                .toEntity(CustomerEvent.class);
    }
}
