package com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Account {
    private UUID id;
    private Integer amount;
    private AccountType accountType;

    public enum AccountType {
        CURRENT,
        SAVINGS
    }
}