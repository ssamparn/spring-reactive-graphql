package com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.function.UnaryOperator;

import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.model.CustomerWithOrder;
import org.springframework.stereotype.Service;
import graphql.schema.DataFetchingFieldSelectionSet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerOrderDataFetcher {

    private final CustomerService customerService;
    private final OrderService orderService;

    public Flux<CustomerWithOrder> customerOrders(DataFetchingFieldSelectionSet field) {
        boolean includeOrders = field.contains("orders");
        log.info("Orders to be included: {}", includeOrders);
        return this.customerService.allCustomers()
                .map(customer -> CustomerWithOrder.create(customer.getId(), customer.getName(), customer.getAge(), customer.getCity(), Collections.emptyList()))
                .transform(this.updateOrdersTransformer(includeOrders));
    }

    private UnaryOperator<Flux<CustomerWithOrder>> updateOrdersTransformer(boolean includeOrders) {
        return includeOrders ?
                customerWithOrderFlux -> customerWithOrderFlux.flatMapSequential(this::fetchOrdersUsingSetter) // fetchOrdersUsingMap() can also be used
                : customerWithOrderFlux -> customerWithOrderFlux;
    }

    private Mono<CustomerWithOrder> fetchOrdersUsingMap(CustomerWithOrder customerWithOrder) {
        return this.orderService.ordersByCustomerName(customerWithOrder.getName())
                .collectList()
                .map(orders -> CustomerWithOrder.create(customerWithOrder.getId(), customerWithOrder.getName(), customerWithOrder.getAge(), customerWithOrder.getCity(), orders));
    }

    /**
     * An alternative version of fetchOrdersUsingMap. Use either fetchOrdersUsingMap() or fetchOrdersUsingSetter()
     * */
    private Mono<CustomerWithOrder> fetchOrdersUsingSetter(CustomerWithOrder customerWithOrder) {
        return this.orderService.ordersByCustomerName(customerWithOrder.getName())
                .collectList()
                .doOnNext(customerWithOrder::setOrders)
                .thenReturn(customerWithOrder);
    }
}
