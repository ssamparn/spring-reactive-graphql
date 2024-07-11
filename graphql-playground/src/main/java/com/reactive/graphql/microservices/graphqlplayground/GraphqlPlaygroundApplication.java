package com.reactive.graphql.microservices.graphqlplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
//				"com.reactive.graphql.microservices.graphqlplayground.helloworld.web",
				"com.reactive.graphql.microservices.graphqlplayground.customer"
		}
)
public class GraphqlPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlPlaygroundApplication.class, args);
	}

}
