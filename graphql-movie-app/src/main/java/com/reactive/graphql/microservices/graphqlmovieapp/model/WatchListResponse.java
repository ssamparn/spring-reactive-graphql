package com.reactive.graphql.microservices.graphqlmovieapp.model;

import lombok.Data;

import java.util.List;

@Data
public class WatchListResponse {

    private Status status;
    private List<Integer> watchList;
}
