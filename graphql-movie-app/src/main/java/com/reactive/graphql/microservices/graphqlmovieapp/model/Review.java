package com.reactive.graphql.microservices.graphqlmovieapp.model;

import lombok.Data;

@Data
public class Review {

    private Integer id;
    private String username;
    private String comment;
    private Double rating;

}
