package dev.jeffersonfreitas.ecom_api.infra.out.persistence.product;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductFilter;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRepositoryInfra implements ProductRepository {

    private final ProductJpaRepository repository;

    public ProductRepositoryInfra(ProductJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByDescription(String description) {
        return repository.existsByDescription(description);
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = ProductMapper.toEntity(product);
        entity = repository.save(entity);
        return ProductMapper.toDomain(entity);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public PageGeneric<Product> getAll(ProductFilter filter, PageableRequestInput pageable) {
        return null;
    }

    @Override
    public Optional<Product> get(String id) {
        return repository.findById(id).map(ProductMapper::toDomain);
    }
}
