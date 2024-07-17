package com.reactive.graphql.microservices.graphqlplayground.lec10.schemadesign.controller;

import java.time.LocalDate;

import com.reactive.graphql.microservices.graphqlplayground.lec10.schemadesign.model.Book;
import com.reactive.graphql.microservices.graphqlplayground.lec10.schemadesign.model.Electronics;
import com.reactive.graphql.microservices.graphqlplayground.lec10.schemadesign.model.Fruit;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class ProductController {

    @QueryMapping
    public Flux<Object> products() {
        return Flux.just(
                Fruit.create("banana", 1, LocalDate.now().plusDays(3)),
                Fruit.create("apple", 2, LocalDate.now().plusDays(5)),
                Electronics.create("mac book", 600, "APPLE"),
                Electronics.create("phone", 400, "SAMSUNG"),
                Book.create("java", 40, "venkat")
        );
    }
}
