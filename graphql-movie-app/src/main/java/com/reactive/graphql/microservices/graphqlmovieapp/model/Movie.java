package com.reactive.graphql.microservices.graphqlmovieapp.model;

import lombok.Data;

@Data
public class Movie {

    private Integer id;
    private String title;
    private Double rating;
    private Integer releaseYear;
    private Genre genre;
}
