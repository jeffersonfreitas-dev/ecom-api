package dev.jeffersonfreitas.ecom_api.infra.out.persistence.order.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import dev.jeffersonfreitas.ecom_api.infra.out.persistence.order.OrderJpaEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItemJpaEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderJpaEntity order;

    @Column(name = "product_id", nullable = false, length = 60)
    private String productId;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false)
    private BigDecimal value;

    @Transient
    private BigDecimal total;

    public BigDecimal getTotal(){
        return BigDecimal.valueOf(quantity).multiply(total);
    }

    public OrderItemJpaEntity(String id, String orderId, String productId, double quantity, BigDecimal value, BigDecimal total){
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.value = value;
        this.total = total;
    }
}
