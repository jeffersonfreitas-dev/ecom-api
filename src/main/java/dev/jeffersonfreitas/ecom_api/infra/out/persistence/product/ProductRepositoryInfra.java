package dev.jeffersonfreitas.ecom_api.infra.out.persistence.product;

import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Product;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Description;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Identity;

public class ProductRepositoryInfra implements ProductRepository {

    private final ProductJpaRepository repository;

    public ProductRepositoryInfra(ProductJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = ProductJpaEntity.from(product);
        entity = repository.save(entity);
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
