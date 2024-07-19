package com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.repository;

import com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
