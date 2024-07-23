package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.serverapp.config;

import com.reactive.graphql.microservices.graphqlplayground.lec15.errorhandling.validation.model.CustomerDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import graphql.schema.TypeResolver;

@Configuration
public class ClassTypeResolverConfig {

    @Bean
    public TypeResolver typeResolver() {
        ClassNameTypeResolver resolver = new ClassNameTypeResolver();
        resolver.addMapping(CustomerDto.class, "Customer");
        return resolver;
    }

    @Bean
    public RuntimeWiringConfigurer configurer(TypeResolver resolver) {
        return c -> c.type("CustomerResponse", b -> b.typeResolver(resolver));
    }
}