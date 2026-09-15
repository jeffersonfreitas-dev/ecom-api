package dev.jeffersonfreitas.ecom_api.infra.out.persistence.order.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItemJpaEntity {

    @Id
    private String id;

    @ManyToOne
    @Column(nullable = false, length = 60)
    @JoinColumn(name = "orderId")
    private String orderId;

    @Column(nullable = false, length = 60)
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
}
