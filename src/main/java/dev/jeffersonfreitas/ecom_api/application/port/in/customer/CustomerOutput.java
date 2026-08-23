package dev.jeffersonfreitas.ecom_api.application.port.in.customer;

import dev.jeffersonfreitas.ecom_api.domain.model.Customer;

public record CustomerOutput(
        String identity,
        String name,
        String email
) {
    public static CustomerOutput from(Customer customer) {
        return new CustomerOutput(
                customer.getUuid().value(),
                customer.getName().value(),
                customer.getEmail().value()
                );
    }
}
