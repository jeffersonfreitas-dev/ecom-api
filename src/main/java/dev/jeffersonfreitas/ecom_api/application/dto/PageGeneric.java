package dev.jeffersonfreitas.ecom_api.application.dto;

import java.util.List;

public class PageGeneric<T> {
    private final List<T> t;
    private final int number;
    private final int size;
    private final int totalElements;
    private final int totalPages;

    public PageGeneric(List<T> t, int number, int size, int totalElements, int totalPages){
        this.t = t;
        this.number = number;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
