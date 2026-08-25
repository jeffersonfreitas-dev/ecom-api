package dev.jeffersonfreitas.ecom_api.infra.out.persistence.product;

import dev.jeffersonfreitas.ecom_api.domain.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    private String id;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private boolean active;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public static ProductJpaEntity from(Product product) {
        return new ProductJpaEntity(
                product.getUuid().value(),
                product.getDescription().value(),
                product.getPrice(),
                product.isActive(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
