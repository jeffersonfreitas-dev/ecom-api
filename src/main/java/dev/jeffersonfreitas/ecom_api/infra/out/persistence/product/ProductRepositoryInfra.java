package dev.jeffersonfreitas.ecom_api.infra.out.persistence.product;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequest;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductFilter;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Product;
import dev.jeffersonfreitas.ecom_api.infra.out.PageRequestMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public PageGeneric<Product> getAll(ProductFilter filter, PageableRequest pageableInput) {
        Pageable pageable = PageRequestMapper.toSpring(pageableInput);
        Specification<ProductJpaEntity> entitySpecification = ProductSpecifications.from(filter);
        Page<ProductJpaEntity> products = repository.findAll(entitySpecification, pageable);
        List<Product> productList = products.stream().map(ProductMapper::toDomain).toList();
        return new PageGeneric<>(
                productList,
                products.getNumber(),
                products.getSize(),
                products.getNumberOfElements(),
                products.getTotalPages()
        );
    }

    @Override
    public Optional<Product> get(String id) {
        return repository.findById(id).map(ProductMapper::toDomain);
    }
}
