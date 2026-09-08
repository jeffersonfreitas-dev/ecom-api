package dev.jeffersonfreitas.ecom_api.application.dto;

import java.util.List;

public record PageGeneric<T>(
        List<T> elements,
        int number,
        int size,
        int totalElements,
        int totalPages
) {
}
