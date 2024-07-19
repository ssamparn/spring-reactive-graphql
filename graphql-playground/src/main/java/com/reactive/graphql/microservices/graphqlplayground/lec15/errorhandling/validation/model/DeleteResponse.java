package com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.model;

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
