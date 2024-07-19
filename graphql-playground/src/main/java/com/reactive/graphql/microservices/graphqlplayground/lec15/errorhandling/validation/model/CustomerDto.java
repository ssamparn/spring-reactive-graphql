package com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.model;

import lombok.Data;

@Data
public class CustomerDto {

    private Integer id;
    private String name;
    private Integer age;
    private String city;
}
