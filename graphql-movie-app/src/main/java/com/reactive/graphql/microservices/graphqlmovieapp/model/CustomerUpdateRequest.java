package com.reactive.graphql.microservices.graphqlmovieapp.model;

import lombok.Data;

@Data
public class CustomerUpdateRequest {
    private Integer id;
    private String name;
    private Genre favoriteGenre;
}
