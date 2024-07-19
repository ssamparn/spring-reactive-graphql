package com.reactive.graphql.microservices.graphqlplayground.lec13.customer.crud.repository;

import com.reactive.graphql.microservices.graphqlplayground.lec13.customer.crud.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
