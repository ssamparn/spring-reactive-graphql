package com.reactive.graphql.microservices.graphqlplayground.lec09.scalartype.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Product {

    private String name;
    private Map<String, String> attributes;

}
