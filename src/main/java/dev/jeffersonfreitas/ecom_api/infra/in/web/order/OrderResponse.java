package dev.jeffersonfreitas.ecom_api.infra.in.web.order;

import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderOutput;

public record OrderResponse(String id) {

    public static OrderResponse from(OrderOutput output) {
        return new OrderResponse(output.uuid());
    }

}
