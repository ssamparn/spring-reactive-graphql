package com.reactive.graphql.microservices.graphqlplayground.lec13.customer.crud.model;

import lombok.Data;

@Data
public class CustomerDto {

    private Integer id;
    private String name;
    private Integer age;
    private String city;
}
