package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class DeleteResponse {

    private Integer id;
    private Status status;
}
