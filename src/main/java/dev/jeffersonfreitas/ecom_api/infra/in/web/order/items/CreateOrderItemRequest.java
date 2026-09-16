package dev.jeffersonfreitas.ecom_api.infra.in.web.order.items;

import java.math.BigDecimal;

public record CreateOrderItemRequest(String productId, double quantity, BigDecimal value) {

}
