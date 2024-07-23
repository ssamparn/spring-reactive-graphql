package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
//        implements CustomerResponse {

    private Integer id;
    private String name;
    private Integer age;
    private String city;
}
