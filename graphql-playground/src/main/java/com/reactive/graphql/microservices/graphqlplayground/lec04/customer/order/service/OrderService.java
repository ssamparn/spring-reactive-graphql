package com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.model.Customer;
import com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.model.CustomerOrder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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

//    public Flux<List<Address>> ordersByCustomerNames(List<String> customerNames) {
//        return Flux.fromIterable(customerNames)
//                .map(name -> map.getOrDefault(name, Collections.emptyList()));
//    }

    /**
     * Here the source used is a Map<CustomerName, List<Address>>.
     * But in real life the source can be anything. Let's consider another source Mono<List<Address>>.
     * I am using another source to demo size mismatch issue. We will get exception as "The size of the promised values MUST be the same size as the key list"
     * Reason: We are receiving 6 customer names from CustomerService, but we have only orders for 3 customers (see map implementation). That is why the error.
     * To fix return default value for each empty signal. Here switchIfEmpty() has been used.
     * */

    // to demo
    public Flux<List<CustomerOrder>> ordersByCustomerNames(List<String> customerNames) {
        return Flux.fromIterable(customerNames)
//                .flatMap(this::fetchOrders);
//                .flatMap(this::fetchOrdersWithDelay); // use this to demo order mismatch
                .flatMapSequential(this::fetchOrdersWithDelay);
    }

    private Mono<List<CustomerOrder>> fetchOrders(String customerName) {
        return Mono.justOrEmpty(map.get(customerName))
                .switchIfEmpty(Mono.just(Collections.emptyList()));
    }

    // But what if there is a delay in the element emission. Observe the behaviour. Sam will have jake's product and John will have Mike's product.
    // This is mismatch is very common in a reactive type. In this case use flatMapSequential().

    private Mono<List<CustomerOrder>> fetchOrdersWithDelay(String customerName) {
        return Mono.justOrEmpty(map.get(customerName))
                .switchIfEmpty(Mono.just(Collections.emptyList()))
                .delayElement(Duration.ofMillis(ThreadLocalRandom.current().nextInt(100, 500)));
    }

    public Mono<Map<Customer, List<CustomerOrder>>> ordersAsCustomerMap(List<Customer> customers) {
        return Flux.fromIterable(customers)
                .map(customer -> Tuples.of(customer, map.getOrDefault(customer.getName(), Collections.emptyList())))
                .collectMap(Tuple2::getT1, Tuple2::getT2);
    }
}
