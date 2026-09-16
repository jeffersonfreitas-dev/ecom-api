package dev.jeffersonfreitas.ecom_api.application.port.in.order.dto;

import java.util.List;

import dev.jeffersonfreitas.ecom_api.infra.in.web.order.CreateOrderRequest;

public record CreateOrderInput(String customerId, List<CreateOrderItemInput> itemInputs) {

    public static CreateOrderInput from(CreateOrderRequest request) {
        List<CreateOrderItemInput> items = request.items().stream().map(i -> new CreateOrderItemInput(i.productId(), i.quantity(), i.value())).toList();
        return new CreateOrderInput(request.customerId(), items);
    }
}
