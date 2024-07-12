package com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.model.Customer;
import com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.model.CustomerOrder;
import com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.service.CustomerService;
import com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.service.OrderService;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @QueryMapping(name = "customers")
    public Flux<Customer> allCustomers() {
        return customerService.allCustomers();
    }

    // @BatchMapping is particularly used to fix N + 1 problem
    @BatchMapping(typeName = "Customer")
    public Flux<List<CustomerOrder>> orders(List<Customer> customers) {
        log.info("Orders method invoked for: {}", customers);
        return this.orderService.ordersByCustomerNames(
                customers.stream()
                        .map(Customer::getName)
                        .toList()
        );
    }
}