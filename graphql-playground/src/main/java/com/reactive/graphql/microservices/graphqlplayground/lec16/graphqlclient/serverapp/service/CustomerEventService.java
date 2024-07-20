package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.serverapp.service;

import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class CustomerEventService {

    private final Sinks.Many<CustomerEvent> customerEventSink = Sinks.many().multicast().onBackpressureBuffer();
    private final Flux<CustomerEvent> customerEventFlux = customerEventSink.asFlux().cache(0);

    public Flux<CustomerEvent> subscribe() {
        return this.customerEventFlux;
    }

    public void emitEvents(CustomerEvent event) {
        this.customerEventSink.tryEmitNext(event);
    }
}