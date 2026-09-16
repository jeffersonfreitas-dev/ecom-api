package dev.jeffersonfreitas.ecom_api.infra.out.persistence.order;

import dev.jeffersonfreitas.ecom_api.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.order.Order;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryInfra implements OrderRepository {

    private final OrderJpaRepository repository;

    public OrderRepositoryInfra(OrderJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Order save(Order order) {
        OrderJpaEntity entity = OrderMapper.toEntity(order);
        entity = repository.save(entity);
        return OrderMapper.toDomain(entity);
    }


    @Override
    public Optional<Order> get(String id) {
        return repository.findById(id).map(OrderMapper::toDomain);
    }
}
