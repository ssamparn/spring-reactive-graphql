package com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerOrder {

    private UUID id;
    private String orderName;
    private String orderDescription;

}
