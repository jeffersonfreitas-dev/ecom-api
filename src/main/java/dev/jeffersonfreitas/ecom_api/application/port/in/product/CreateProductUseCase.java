package dev.jeffersonfreitas.ecom_api.application.port.in.product;

public interface CreateProductUseCase {
    ProductOutput execute(CreateProductInput input);
}
