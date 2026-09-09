package dev.jeffersonfreitas.ecom_api.application.port.in.product;

import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductOutput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.UpdateProductInput;

public interface UpdateProductUseCase {
    ProductOutput execute(String id, UpdateProductInput input);
}
