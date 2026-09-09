package dev.jeffersonfreitas.ecom_api.application.port.in.product;

import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductOutput;

public interface GetProductUseCase {
    ProductOutput execute(String id);
}
