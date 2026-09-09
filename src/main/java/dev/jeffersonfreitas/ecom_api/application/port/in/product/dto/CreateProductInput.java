package dev.jeffersonfreitas.ecom_api.application.port.in.product.dto;

import java.math.BigDecimal;

public record CreateProductInput(String description, BigDecimal price) {
}
