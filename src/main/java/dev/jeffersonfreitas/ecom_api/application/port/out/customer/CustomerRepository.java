package dev.jeffersonfreitas.ecom_api.application.port.out.customer;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequest;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerFilter;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;

import java.util.Optional;

public interface CustomerRepository {

    boolean existsByEmail(String email);
    Customer save(Customer customer);
    Optional<Customer> getById(String id);
    PageGeneric<Customer> findAll(CustomerFilter filter, PageableRequest pageableInput);
    void delete(String id);
}
