package dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto;

public record UpdateCustomerInput(
        String name,
        String email
) {
}
