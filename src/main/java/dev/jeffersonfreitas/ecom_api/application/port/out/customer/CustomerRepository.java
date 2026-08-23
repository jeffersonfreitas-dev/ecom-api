package dev.jeffersonfreitas.ecom_api.application.port.out.customer;

import dev.jeffersonfreitas.ecom_api.domain.model.Customer;

public interface CustomerRepository {

    boolean existsByEmail(String email);
    Customer save(Customer customer);
}
