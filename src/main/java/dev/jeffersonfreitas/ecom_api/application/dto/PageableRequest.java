package dev.jeffersonfreitas.ecom_api.application.dto;


import java.util.List;

public record PageableRequest(
        int page,
        int size,
        List<SortOrder> sort
) {

    public static PageableRequest create(int page, int size, List<SortOrder> sort){
        return new PageableRequest(page, size, sort);
    }
}
