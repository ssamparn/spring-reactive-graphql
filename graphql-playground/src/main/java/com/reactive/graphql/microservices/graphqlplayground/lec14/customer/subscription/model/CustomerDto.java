package com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.model;

import lombok.Data;

@Data
public class CustomerDto {

    private Integer id;
    private String name;
    private Integer age;
    private String city;
}
