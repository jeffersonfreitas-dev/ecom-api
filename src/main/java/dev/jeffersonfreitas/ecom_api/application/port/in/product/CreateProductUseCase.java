package dev.jeffersonfreitas.ecom_api.application.port.in.product;

import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.CreateProductInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductOutput;

public interface CreateProductUseCase {
    ProductOutput execute(CreateProductInput input);
}
