package dev.jeffersonfreitas.ecom_api.application.port.in.product;

import java.math.BigDecimal;

public record ProductOutput(
        String uuid,
        String name,
        String description,
        BigDecimal price,
        String status
) {
}
