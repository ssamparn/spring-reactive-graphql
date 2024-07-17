package com.reactive.graphql.microservices.graphqlplayground.lec11.union.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Fruit {

    private String description;
    private LocalDate expiryDate;
}
