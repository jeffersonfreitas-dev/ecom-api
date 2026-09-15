package dev.jeffersonfreitas.ecom_api.application.port.in.order.dto;

import dev.jeffersonfreitas.ecom_api.domain.model.order.Order;

public record OrderOutput(
        String uuid
) {
    public static OrderOutput from(Order order) {
        return new OrderOutput(order.uuid().value());
    }
}
