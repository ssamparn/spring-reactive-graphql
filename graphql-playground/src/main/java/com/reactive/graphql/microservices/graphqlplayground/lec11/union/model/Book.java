package com.reactive.graphql.microservices.graphqlplayground.lec11.union.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Book {

    private String title;
    private String author;
}
