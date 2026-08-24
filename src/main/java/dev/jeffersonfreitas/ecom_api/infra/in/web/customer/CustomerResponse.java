package dev.jeffersonfreitas.ecom_api.infra.in.web.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerOutput;

public record CustomerResponse(String id, String name, String email) {

    public static CustomerResponse from(CustomerOutput output) {
        return new CustomerResponse(output.identity(), output.name(), output.email());
    }
}
