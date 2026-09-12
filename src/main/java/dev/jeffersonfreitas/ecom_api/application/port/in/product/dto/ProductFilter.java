package dev.jeffersonfreitas.ecom_api.application.port.in.product.dto;

import java.math.BigDecimal;

public record ProductFilter(
        String description,
        BigDecimal initPrice,
        BigDecimal finalPrice
) {
}
