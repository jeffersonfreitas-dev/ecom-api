package dev.jeffersonfreitas.ecom_api.application.port.out.order;

import dev.jeffersonfreitas.ecom_api.domain.model.order.Order;

public interface OrderRepository {
    Order save(Order order);
}
