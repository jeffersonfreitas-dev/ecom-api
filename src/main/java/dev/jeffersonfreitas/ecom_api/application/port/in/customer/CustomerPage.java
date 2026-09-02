package dev.jeffersonfreitas.ecom_api.application.port.in.customer;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;

import java.util.List;

public class CustomerPage extends PageGeneric<Customer> {
    public CustomerPage(List<Customer> customers, int number, int size, int totalElements, int totalPages) {
        super(customers, number, size, totalElements, totalPages);
    }
}
