package dev.jeffersonfreitas.ecom_api.infra.in.web.product;

import java.math.BigDecimal;

public record CreateProductRequest(
        String name,
        String description,
        BigDecimal price
) {
}
