package com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.model.CustomerWithOrder;
import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.service.CustomerOrderDataFetcher;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import graphql.schema.DataFetchingFieldSelectionSet;
import reactor.core.publisher.Flux;

/**
 * This is an alternative implementation of CustomerController.
 * In CustomerOrderController, we are doing mapping or binding of orders ourselves.
 * Use one or either. In this implementation we will get a performance like the Rest Implementation. Around 6 seconds.
 * */
@Controller
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerOrderDataFetcher customerOrderDataFetcher;

    @QueryMapping(name = "customers")
    public Flux<CustomerWithOrder> customerWithOrders(DataFetchingFieldSelectionSet orderField) {
        return this.customerOrderDataFetcher.customerOrders(orderField);
    }
}