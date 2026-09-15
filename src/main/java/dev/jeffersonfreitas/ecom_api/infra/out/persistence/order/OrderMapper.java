package dev.jeffersonfreitas.ecom_api.infra.out.persistence.order;

import dev.jeffersonfreitas.ecom_api.application.exception.BusinessException;
import dev.jeffersonfreitas.ecom_api.domain.model.order.Order;
import dev.jeffersonfreitas.ecom_api.domain.model.order.OrderItem;
import dev.jeffersonfreitas.ecom_api.infra.out.persistence.order.items.OrderItemJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public static Order toDomain(OrderJpaEntity entity){
        if(entity == null){
            throw new BusinessException("Entidade não pode ser nulo ao converter para o dominio");
        }

        List<OrderItem> items = toDomainItem(entity.getItems());
        return new Order(entity.getId(), entity.getCustomerId(), entity.getDate(), entity.isActive(), entity.getTotal(), entity.getStatus(), items);
    }

    public static OrderJpaEntity toEntity(Order order){
        if(order == null){
            throw new BusinessException("Dominio não pode ser nulo ao converter para a entidade");
        }
        List<OrderItemJpaEntity> items = toEntityItem(order.uuid().value(), order.items());
        return new OrderJpaEntity(
                order.uuid().value(),
                order.customerId().value(),
                order.date(),
                order.active(),
                order.total(),
                order.status().name(),
                items);
    }

    private static List<OrderItemJpaEntity> toEntityItem(String orderId, List<OrderItem> items) {
        return items.stream().map(item ->
                new OrderItemJpaEntity(
                        item.uuid().value(),
                        orderId,
                        item.productId().value(),
                        item.quantity().value(),
                        item.value(),
                        item.total()));
    }

    private static List<OrderItem> toDomainItem(List<OrderItemJpaEntity> items) {
        return items.stream().map(
                item -> new OrderItem(
                        item.getId(),
                        item.getProductId(),
                        item.getQuantity(),
                        item.getValue(),
                        item.getTotal())).toList();
    }
}
