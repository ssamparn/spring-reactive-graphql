package com.reactive.graphql.microservices.graphqlmovieapp.model;

import lombok.Data;

import com.reactive.graphql.microservices.graphqlmovieapp.model.Genre;

@Data
public class CustomerUpdateRequest {
    private Integer id;
    private String name;
    private Genre favoriteGenre;
}
