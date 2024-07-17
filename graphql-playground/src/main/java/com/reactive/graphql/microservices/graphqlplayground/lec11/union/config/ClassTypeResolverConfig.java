package com.reactive.graphql.microservices.graphqlplayground.lec11.union.config;

import com.reactive.graphql.microservices.graphqlplayground.lec11.union.model.Fruit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import graphql.schema.TypeResolver;

/**
 * This configuration class is not needed to resolve classTypes
 * as we have not named our classes any different from what we declare in graphql schema
 * */
@Configuration
public class ClassTypeResolverConfig {

    @Bean
    public RuntimeWiringConfigurer configurer(TypeResolver resolver){
        return c -> c.type("Result", b -> b.typeResolver(resolver));
    }

    @Bean
    public TypeResolver typeResolver(){
        ClassNameTypeResolver resolver = new ClassNameTypeResolver();
        resolver.addMapping(Fruit.class, "Fruit");
        return resolver;
    }

}
