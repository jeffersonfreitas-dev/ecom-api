package dev.jeffersonfreitas.ecom_api.infra.in.web.product;

import dev.jeffersonfreitas.ecom_api.application.port.in.product.ProductOutput;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(
        String name,
        String description,
        BigDecimal price,
        boolean status,
        Instant createdAt,
        Instant updatedAt
) {
    public static ProductResponse from(ProductOutput output) {
        return new ProductResponse(
                output.uuid(),
                output.description(),
                output.price(),
                output.active(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}
