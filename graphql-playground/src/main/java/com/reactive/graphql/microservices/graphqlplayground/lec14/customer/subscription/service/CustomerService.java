package com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.service;

import lombok.RequiredArgsConstructor;

import com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.entity.Customer;
import com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.model.Action;
import com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.model.CustomerDto;
import com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.model.CustomerEvent;
import com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.model.DeleteResponse;
import com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.model.Status;
import com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerEventService customerEventService;
    private final CustomerRepository customerRepository;

    public Flux<CustomerDto> allCustomers() {
        return this.customerRepository.findAll()
                .map(this::toDto);
    }

    public Mono<CustomerDto> customerById(Integer id) {
        return this.customerRepository.findById(id)
                .map(this::toDto);
    }

    public Mono<CustomerDto> createCustomer(CustomerDto dto) {
        return Mono.just(dto)
                .map(this::toEntity)
                .flatMap(this.customerRepository::save)
                .map(this::toDto)
                .doOnNext(customer -> this.customerEventService.emitEvents(CustomerEvent.create(customer.getId(), Action.CREATED)));
    }

    public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto dto) {
        return this.customerRepository.findById(id)
                .map(c -> this.toEntity(id, dto))
                .flatMap(this.customerRepository::save)
                .map(this::toDto)
                .doOnNext(customer -> this.customerEventService.emitEvents(CustomerEvent.create(customer.getId(), Action.UPDATED)));
    }

    public Mono<DeleteResponse> deleteCustomer(Integer id) {
        return this.customerRepository.deleteById(id)
                .doOnSuccess(r -> this.customerEventService.emitEvents(CustomerEvent.create(id, Action.DELETED)))
                .thenReturn(DeleteResponse.create(id, Status.SUCCESS))
                .onErrorReturn(DeleteResponse.create(id, Status.FAILURE));
    }

    public Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }

    public Customer toEntity(Integer id, CustomerDto dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        customer.setId(id);
        return customer;
    }

    public CustomerDto toDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        BeanUtils.copyProperties(customer, dto);
        return dto;
    }

}
