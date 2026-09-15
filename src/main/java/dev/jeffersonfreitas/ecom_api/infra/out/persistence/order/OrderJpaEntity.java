package dev.jeffersonfreitas.ecom_api.infra.out.persistence.order;

import dev.jeffersonfreitas.ecom_api.infra.out.persistence.order.items.OrderItemJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    private String id;

    @Column(nullable = false, length = 60)
    private String customerId;

    @Column(nullable = false)
    private Instant date;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<OrderItemJpaEntity> items;

}
