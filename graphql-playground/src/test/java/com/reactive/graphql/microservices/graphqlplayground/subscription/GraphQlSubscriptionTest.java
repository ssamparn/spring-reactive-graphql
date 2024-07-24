package com.reactive.graphql.microservices.graphqlplayground.subscription;

import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.Action;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.graphql.test.tester.WebSocketGraphQlTester;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.test.StepVerifier;

// Run this test while only enabling package lec14
@Disabled
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureHttpGraphQlTester
public class GraphQlSubscriptionTest {

    private static final String WS_PATH = "ws://localhost:8080/graphql";

    @Autowired
    private HttpGraphQlTester graphQlTestClient;

    @Test
    public void subscriptionTest(){
        var websocketClient = WebSocketGraphQlTester
                .builder(WS_PATH, new ReactorNettyWebSocketClient())
                .build();

        // delete a customer
        this.graphQlTestClient.documentName("crud-operations")
                .operationName("DeleteCustomer")
                .variable("id", 1)
                .executeAndVerify(); // there are no errors

        // consume the delete event
        websocketClient.documentName("subscription-test")
                .executeSubscription()
                .toFlux("customerEvents", CustomerEvent.class)
                .take(1)
                .as(StepVerifier::create)
                .consumeNextWith(e -> Assertions.assertThat(e.getAction()).isEqualTo(Action.DELETED))
                .verifyComplete();
    }
}
