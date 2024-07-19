package com.reactive.graphql.microservices.graphqlplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(
		scanBasePackages = {
//				"com.reactive.graphql.microservices.graphqlplayground.lec01.helloworld"
//				"com.reactive.graphql.microservices.graphqlplayground.lec02.customer"
//				"com.reactive.graphql.microservices.graphqlplayground.lec03.customer.order"
//				"com.reactive.graphql.microservices.graphqlplayground.lec04.customer.order"
//				"com.reactive.graphql.microservices.graphqlplayground.lec05.customer.address"
//				"com.reactive.graphql.microservices.graphqlplayground.lec06.customer.order"
//				"com.reactive.graphql.microservices.graphqlplayground.lec07.customer.order"
//				"com.reactive.graphql.microservices.graphqlplayground.lec08.fieldglobpattern"
//				"com.reactive.graphql.microservices.graphqlplayground.lec09.scalartype"
//				"com.reactive.graphql.microservices.graphqlplayground.lec10.schemadesign"
//				"com.reactive.graphql.microservices.graphqlplayground.lec11.union"
//				"com.reactive.graphql.microservices.graphqlplayground.lec12.operationcaching"
//				"com.reactive.graphql.microservices.graphqlplayground.lec13.customer.crud"
//				"com.reactive.graphql.microservices.graphqlplayground.lec14.customer.subscription"
				"com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation"
		}
)
@EnableR2dbcRepositories(basePackages = "com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation")
public class GraphqlPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlPlaygroundApplication.class, args);
	}

}
