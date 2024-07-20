package com.reactive.graphql.microservices.graphqlplayground.lec16.graphqlclient.common.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GenericResponse<T> {
    private final T data;
    private final String errorMessage;
    private final boolean dataPresent;

    public GenericResponse(T data) {
        this.data = data;
        this.errorMessage = null;
        this.dataPresent = true;
    }

    public GenericResponse(String errorMessage) {
        this.data = null;
        this.errorMessage = errorMessage;
        this.dataPresent = false;
    }
}
