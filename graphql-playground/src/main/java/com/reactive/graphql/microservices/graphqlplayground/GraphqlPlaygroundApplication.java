package com.reactive.graphql.microservices.graphqlplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
//				"com.reactive.graphql.microservices.graphqlplayground.lec01.helloworld"
//				"com.reactive.graphql.microservices.graphqlplayground.lec02.customer"
//				"com.reactive.graphql.microservices.graphqlplayground.lec03.customer.order"
//				"com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order"
				"com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address"
		}
)
public class GraphqlPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlPlaygroundApplication.class, args);
	}

}
