package com.reactive.graphql.microservices.graphqlmovieapp.dto;

import lombok.Data;

import com.reactive.graphql.microservices.graphqlmovieapp.model.Genre;

@Data
public class CustomerInput {

    private Integer id;
    private String name;
    private Genre favoriteGenre;

}
