package dev.jeffersonfreitas.ecom_api.application.port.out.order;

import java.util.Optional;

import dev.jeffersonfreitas.ecom_api.domain.model.order.Order;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> get(String id);
    void delete(String id);
}
