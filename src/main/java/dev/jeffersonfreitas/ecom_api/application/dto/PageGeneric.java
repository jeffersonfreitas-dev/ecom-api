package dev.jeffersonfreitas.ecom_api.application.dto;

import java.util.List;
import java.util.function.Function;

public record PageGeneric<T>(
        List<T> elements,
        int number,
        int size,
        int totalElements,
        int totalPages
) {

    public <R> PageGeneric<R> map(Function<T, R> mapper){
        return new PageGeneric<>(
                elements.stream().map(mapper).toList(),
                number,
                size,
                totalElements,
                totalPages
        );
    }
}
