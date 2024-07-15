package com.reactive.graphql.microservices.graphqlplayground.lec08.fieldglobpattern;

import lombok.extern.slf4j.Slf4j;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import graphql.schema.DataFetchingFieldSelectionSet;

@Slf4j
@Controller
public class FieldGlobPatternController {

    @QueryMapping
    public Object level1(DataFetchingFieldSelectionSet selectionSet){
        log.info("{}", selectionSet.contains("level2"));
        log.info("{}", selectionSet.contains("level3"));
        log.info("{}", selectionSet.contains("level2/level3"));
        log.info("{}", selectionSet.contains("**/level3"));
        log.info("{}", selectionSet.contains("level2/**/level5"));

        return null;
    }

}
