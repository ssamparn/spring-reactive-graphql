package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.serverapp.repository;

import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.serverapp.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
