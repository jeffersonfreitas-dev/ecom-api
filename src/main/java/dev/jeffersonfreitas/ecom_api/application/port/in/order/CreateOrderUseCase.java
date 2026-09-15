package dev.jeffersonfreitas.ecom_api.application.port.in.order;

import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.CreateOrderInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderOutput;

public interface CreateOrderUseCase {

    OrderOutput execute(CreateOrderInput input);
}
