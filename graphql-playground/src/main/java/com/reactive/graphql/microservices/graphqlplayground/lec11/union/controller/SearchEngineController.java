package com.reactive.graphql.microservices.graphqlplayground.lec11.union.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.reactive.graphql.microservices.graphqlplayground.lec11.union.model.Book;
import com.reactive.graphql.microservices.graphqlplayground.lec11.union.model.Electronics;
import com.reactive.graphql.microservices.graphqlplayground.lec11.union.model.Fruit;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import io.netty.util.internal.ThreadLocalRandom;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class SearchEngineController {

    List<Object> objects = List.of(
            Fruit.create("banana", LocalDate.now().plusDays(3)),
            Fruit.create("apple", LocalDate.now().plusDays(5)),
            Electronics.create("mac book", 600, "APPLE"),
            Electronics.create("phone", 400, "SAMSUNG"),
            Book.create("java", "venkat")
    );

    @QueryMapping
    public Flux<Object> search() {
        return Mono.fromSupplier(() -> new ArrayList<>(objects))
                .doOnNext(Collections::shuffle) // shuffling the order
                .flatMapMany(Flux::fromIterable)
                // .flatMapIterable(Function.identity()) // we can also use flatMapIterable(Function.identity()) instead of flatMapMany
                .take(ThreadLocalRandom.current().nextInt(1, objects.size())); // take any number of random item
    }
}
