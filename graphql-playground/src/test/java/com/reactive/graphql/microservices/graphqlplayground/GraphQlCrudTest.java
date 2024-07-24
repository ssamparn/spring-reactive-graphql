package com.reactive.graphql.microservices.graphqlplayground;

import java.util.Map;

import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.CustomerDto;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.DeleteResponse;
import com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

// Run this test while only enabling package lec16
@SpringBootTest
@AutoConfigureHttpGraphQlTester
public class GraphQlCrudTest {

    @Autowired
    private HttpGraphQlTester graphQlTestClient;

    @Test
    public void allCustomersTest(){
        var query = """
					query GetAllCustomers {
						customers {
						    id
							name
							age
							city
						}
					}
				""";

        this.graphQlTestClient.document(query)
                .execute()
                .path("customers").entityList(Object.class).hasSize(4)
                .path("customers.[0].name").entity(String.class).isEqualTo("sam");
    }

	@Test
	public void customerByIdTest(){
		this.graphQlTestClient.documentName("crud-operations")
				.variable("id", 1)
				.operationName("GetCustomerById")
				.execute()
				.path("response.id").entity(Integer.class).isEqualTo(1)
				.path("response.name").entity(String.class).isEqualTo("sam")
				.path("response.age").entity(Integer.class).isEqualTo(10);
	}

	@Test
	public void createCustomerTest(){
		this.graphQlTestClient.documentName("crud-operations")
				.variable("customer", CustomerDto.create(null, "michael", 55, "seattle"))
				.operationName("CreateNewCustomer")
				.execute()
				.path("response.id").entity(Integer.class).isEqualTo(5)
				.path("response.name").entity(String.class).isEqualTo("michael")
				.path("response.age").entity(Integer.class).isEqualTo(55);
	}

	@Test
	public void updateCustomerTest(){
		this.graphQlTestClient.documentName("crud-operations")
				.variables(Map.of("id", 2, "customer", CustomerDto.create(null, "obie", 45, "dallas")))
				.operationName("UpdateCustomer")
				.execute()
				.path("response.id").entity(Integer.class).isEqualTo(2)
				.path("response.name").entity(String.class).isEqualTo("obie")
				.path("response.city").entity(String.class).isEqualTo("dallas")
				.path("response").entity(Object.class).satisfies(System.out::println);
	}

	@Test
	public void deleteCustomerTest(){
		this.graphQlTestClient.documentName("crud-operations")
				.variable("id", 3)
				.operationName("DeleteCustomer")
				.execute()
				.path("response").entity(DeleteResponse.class).satisfies(r -> {
					Assertions.assertThat(r.getId()).isEqualTo(3);
					Assertions.assertThat(r.getStatus()).isEqualTo(Status.SUCCESS);
				});
	}

}
