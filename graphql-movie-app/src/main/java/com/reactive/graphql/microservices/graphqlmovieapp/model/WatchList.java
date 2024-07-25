package com.reactive.graphql.microservices.graphqlmovieapp.model;

import lombok.Data;

@Data
public class WatchList {

    private Integer customerId;
    private Integer movieId;

}
