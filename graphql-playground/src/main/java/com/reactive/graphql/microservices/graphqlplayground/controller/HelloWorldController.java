package com.reactive.graphql.microservices.graphqlplayground.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class HelloWorldController {

    @QueryMapping(name = "sayHello")
    public Mono<String> helloWorld() {
        return Mono.just("Hello World");
    }

    @QueryMapping(name = "sayHelloTo")
    public Mono<String> helloClient(@Argument String name, @Argument Integer age) {
        return Mono.fromSupplier(() -> "Client name is " + name + " who is of age " + age.toString());
    }
}
