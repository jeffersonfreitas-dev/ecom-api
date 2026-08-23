package dev.jeffersonfreitas.ecom_api.application.port.in.customer;

public record CreateCustomerInput(
        String name,
        String email
) {
}
