package dev.jeffersonfreitas.ecom_api.infra.in.web.order;

import java.util.List;

import dev.jeffersonfreitas.ecom_api.infra.in.web.order.items.CreateOrderItemRequest;

public record CreateOrderRequest(String customerId, List<CreateOrderItemRequest> items) {

}
