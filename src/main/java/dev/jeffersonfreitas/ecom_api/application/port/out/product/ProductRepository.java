package dev.jeffersonfreitas.ecom_api.application.port.out.product;

import dev.jeffersonfreitas.ecom_api.domain.model.Product;

public interface ProductRepository {

    boolean existsByDescription(String description);
    Product save(Product product);
}
