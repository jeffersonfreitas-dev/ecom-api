package dev.jeffersonfreitas.ecom_api.application.dto;


import java.util.List;

public record PageableRequestInput(
        int page,
        int size,
        List<SortOrder> sort
) {
}
