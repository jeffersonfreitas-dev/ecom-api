package dev.jeffersonfreitas.ecom_api.application.port.out.product;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductFilter;
import dev.jeffersonfreitas.ecom_api.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {

    boolean existsByDescription(String description);
    Product save(Product product);
    void delete(String id);
    PageGeneric<Product> getAll(ProductFilter filter, PageableRequestInput pageable);
    Optional<Product> get(String id);
}
