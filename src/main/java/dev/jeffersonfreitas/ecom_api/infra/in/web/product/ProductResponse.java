package dev.jeffersonfreitas.ecom_api.infra.in.web.product;

import dev.jeffersonfreitas.ecom_api.application.port.in.product.ProductOutput;

import java.math.BigDecimal;

public record ProductResponse(
        String name,
        String description,
        BigDecimal price,
        String status
) {
    public static ProductResponse from(ProductOutput output) {
        return new ProductResponse(
                output.uuid(),
                output.description(),
                output.price(),
                output.status()
        );
    }
}
