package com.reactive.graphql.microservices.graphqlplayground.lec03.customer.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.reactive.graphql.microservices.graphqlplayground.lec03.customer.order.model.Customer;
import com.reactive.graphql.microservices.graphqlplayground.lec03.customer.order.model.CustomerOrder;
import com.reactive.graphql.microservices.graphqlplayground.lec03.customer.order.service.CustomerService;
import com.reactive.graphql.microservices.graphqlplayground.lec03.customer.order.service.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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

    @SchemaMapping(typeName = "Customer")
    public Flux<CustomerOrder> orders(Customer customer, @Argument Integer limit) {
        log.info("Orders method invoked for: {}", customer.getName());
        return orderService.ordersByCustomerName(customer.getName())
                .take(limit);
    }
}
