package com.reactive.graphql.microservices.graphqlplayground.customer.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AgeRangeFilter {
    private Integer minAge;
    private Integer maxAge;
}
