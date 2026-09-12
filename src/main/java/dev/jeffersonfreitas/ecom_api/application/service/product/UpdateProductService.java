package dev.jeffersonfreitas.ecom_api.application.service.product;

import dev.jeffersonfreitas.ecom_api.application.exception.ProductNotFoundException;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.UpdateProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductOutput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.UpdateProductInput;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Product;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Description;

import java.time.Instant;

public class UpdateProductService implements UpdateProductUseCase {

    private final ProductRepository repository;

    public UpdateProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductOutput execute(String id, UpdateProductInput input) {
        Product product = repository.get(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado para realizar a alteração"));

        Description description = new Description(input.description());
        Product updatedProduct = new Product(
                product.getUuid(),
                description,
                product.getPrice(),
                product.isActive(),
                product.getCreatedAt(),
                Instant.now());
        repository.save(updatedProduct);
        return ProductOutput.from(updatedProduct);
    }
}
