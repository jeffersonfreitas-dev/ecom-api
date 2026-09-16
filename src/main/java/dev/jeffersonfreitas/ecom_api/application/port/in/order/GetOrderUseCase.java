package dev.jeffersonfreitas.ecom_api.application.port.in.order;

import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderOutput;

public interface GetOrderUseCase {

    OrderOutput execute(String id);

}
