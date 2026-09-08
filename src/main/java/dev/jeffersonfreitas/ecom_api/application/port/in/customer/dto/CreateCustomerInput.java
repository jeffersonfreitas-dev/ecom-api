package dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto;

public record CreateCustomerInput(
        String name,
        String email
) {
}
