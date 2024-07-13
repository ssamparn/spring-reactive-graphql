package com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.service;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.model.CustomerOrder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class OrderService {

    private final Map<String, List<CustomerOrder>> map = Map.of(
            "Sam", List.of(
                    CustomerOrder.create(UUID.randomUUID(), "sam-product-1", "sam-product-1-description"),
                    CustomerOrder.create(UUID.randomUUID(), "sam-product-2", "sam-product-2-description")
            ),
            "Mike", List.of(
                    CustomerOrder.create(UUID.randomUUID(), "mike-product-1", "mike-product-1-description"),
                    CustomerOrder.create(UUID.randomUUID(), "mike-product-2", "mike-product-2-description"),
                    CustomerOrder.create(UUID.randomUUID(), "mike-product-3", "mike-product-3-description")
            ),
            "Jake", List.of(
                    CustomerOrder.create(UUID.randomUUID(), "jake-product-1", "jake-product-1-description"),
                    CustomerOrder.create(UUID.randomUUID(), "jake-product-2", "jake-product-2-description"),
                    CustomerOrder.create(UUID.randomUUID(), "jake-product-3", "jake-product-3-description"),
                    CustomerOrder.create(UUID.randomUUID(), "jake-product-4", "jake-product-4-description")
            )

    );

    public Flux<CustomerOrder> ordersByCustomerName(String customerName) {
        return Flux.fromIterable(map.getOrDefault(customerName, Collections.emptyList()))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(order -> log.info("Emitted order: {} for customer: {}", order.getOrderName(), customerName));
    }
}