package com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.controller;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.model.CustomerWithOrder;
import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.service.OrderService;
import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * In this implementation we will get a performance of around 6 seconds.
 * In CustomerOrderController implementation, we are trying to match this performance.
 * */
@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping("/customers")
    public Flux<CustomerWithOrder> customerOrders() {
        return this.customerService.allCustomers()
                .flatMap(customer -> this.orderService.ordersByCustomerName(customer.getName())
                                .collectList()
                                .map(orders -> CustomerWithOrder.create(customer.getId(), customer.getName(), customer.getAge(), customer.getCity(), orders)));
    }
}