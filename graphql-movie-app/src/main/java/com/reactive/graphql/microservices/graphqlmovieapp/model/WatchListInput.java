package com.reactive.graphql.microservices.graphqlmovieapp.model;

import lombok.Data;

@Data
public class WatchListInput {

    private Integer customerId;
    private Integer movieId;
}
