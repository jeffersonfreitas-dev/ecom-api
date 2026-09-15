package dev.jeffersonfreitas.ecom_api.application.port.in.order.dto;

import java.util.List;

public record CreateOrderInput(String customerId, List<CreateOrderItemInput> itemInputs) {
}
