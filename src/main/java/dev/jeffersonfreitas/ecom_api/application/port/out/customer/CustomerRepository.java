package dev.jeffersonfreitas.ecom_api.application.port.out.customer;

import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerPage;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.getAll.CustomerFilter;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;

import java.util.Optional;

public interface CustomerRepository {

    boolean existsByEmail(String email);
    Customer save(Customer customer);
    Optional<Customer> getById(String id);
    CustomerPage findAll(CustomerFilter filter, PageableRequestInput pageableInput);
}
