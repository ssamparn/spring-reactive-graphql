package com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.model.Customer;
import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.model.CustomerOrder;
import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.service.CustomerService;
import com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order.service.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

/**
 * This is an alternative implementation of CustomerOrderController.
 * In CustomerController, we are letting GraphQL do the binding of orders.
 * Use one or either. In this implementation we will get bad performance. Around 10 seconds.
 * */
@Slf4j
@Controller
@RequiredArgsConstructor
public class CustomerController {

//    private final CustomerService customerService;
//    private final OrderService orderService;
//
//    @QueryMapping(name = "customers")
//    public Flux<Customer> allCustomers() {
//        return this.customerService.allCustomers();
//    }
//
//    @SchemaMapping(typeName = "Customer")
//    public Flux<CustomerOrder> orders(Customer customer) {
//        log.info("Orders method invoked for: {}", customer.getName());
//        return this.orderService.ordersByCustomerName(customer.getName());
//    }
}
