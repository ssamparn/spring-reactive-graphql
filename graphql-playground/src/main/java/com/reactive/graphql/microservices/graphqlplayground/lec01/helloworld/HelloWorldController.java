package com.reactive.graphql.microservices.graphqlplayground.lec01.helloworld;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class HelloWorldController {

    @QueryMapping(name = "sayHello")
    public Mono<String> helloWorld() {
        return Mono.just("Hello World!!!")
                .delayElement(Duration.ofMillis(500));
    }

    @QueryMapping(name = "sayHelloTo")
    public Mono<String> helloClient(@Argument(name = "name") String clientName, @Argument(name = "age") Integer age) {
        return Mono.fromSupplier(() -> "Client name is " + clientName + " who is of age " + age.toString())
                .delayElement(Duration.ofMillis(800));
    }

    @QueryMapping(name = "randomInteger")
    public Mono<Integer> randomInteger() {
        return Mono.just(ThreadLocalRandom.current().nextInt(1, 100))
                .delayElement(Duration.ofMillis(1000));
    }
}
