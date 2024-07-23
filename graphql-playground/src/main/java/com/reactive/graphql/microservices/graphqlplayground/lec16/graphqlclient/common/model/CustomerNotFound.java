package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerNotFound {

    private Integer id;
    private String message;
}