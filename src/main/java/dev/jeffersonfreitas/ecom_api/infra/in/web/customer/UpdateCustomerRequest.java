package dev.jeffersonfreitas.ecom_api.infra.in.web.customer;

public record UpdateCustomerRequest(
        String name,
        String email
) {
}
