package dev.jeffersonfreitas.ecom_api.application.port.in.order.dto;

import java.math.BigDecimal;

public record CreateOrderItemInput(String productId, double quantity, BigDecimal value) {
}
