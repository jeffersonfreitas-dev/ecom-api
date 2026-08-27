package dev.jeffersonfreitas.ecom_api.application.port.in.customer.create;

public record CreateCustomerInput(
        String name,
        String email
) {
}
