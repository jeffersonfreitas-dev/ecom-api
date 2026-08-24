package dev.jeffersonfreitas.ecom_api.infra.in.web.customer;

public record CreateCustomerRequest(
        String name,
        String email
) {
}
