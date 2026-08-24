package dev.jeffersonfreitas.ecom_api.application.port.in.product;

import java.math.BigDecimal;

public record CreateProductInput(String name, String description, BigDecimal price) {
}
