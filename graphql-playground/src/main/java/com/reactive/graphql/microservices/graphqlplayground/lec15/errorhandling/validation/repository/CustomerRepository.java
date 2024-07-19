package com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.repository;

import com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
