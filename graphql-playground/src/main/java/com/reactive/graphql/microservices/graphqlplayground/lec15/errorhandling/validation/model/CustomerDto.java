package com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    private Integer id;
    private String name;
    private Integer age;
    private String city;
}
