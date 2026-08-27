package dev.jeffersonfreitas.ecom_api.infra.out.persistence.product;

import dev.jeffersonfreitas.ecom_api.domain.model.Product;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Description;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Identity;
import org.springframework.stereotype.Component;

@Component
public final class ProductMapper {

    public static ProductJpaEntity toEntity(Product product) {
        return new ProductJpaEntity(
                product.getUuid().value(),
                product.getDescription().value(),
                product.getPrice(),
                product.isActive(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public static Product toDomain(ProductJpaEntity entity) {
        return new Product(
                new Identity(entity.getId()),
                new Description(entity.getDescription()),
                entity.getPrice(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
