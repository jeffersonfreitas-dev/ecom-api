package dev.jeffersonfreitas.ecom_api.application.port.in.product;

import dev.jeffersonfreitas.ecom_api.domain.model.Product;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductOutput(
        String uuid,
        String description,
        BigDecimal price,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {
    public static ProductOutput from(Product product) {
        return new ProductOutput(
                product.getUuid().value(),
                product.getDescription().value(),
                product.getPrice(),
                product.isActive(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
