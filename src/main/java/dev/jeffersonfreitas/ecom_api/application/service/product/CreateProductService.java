package dev.jeffersonfreitas.ecom_api.application.service.product;

import dev.jeffersonfreitas.ecom_api.application.port.in.product.CreateProductInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.CreateProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.ProductOutput;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.domain.exception.ProductAlreadyExistsException;
import dev.jeffersonfreitas.ecom_api.domain.model.Product;

public class CreateProductService implements CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductOutput execute(CreateProductInput input) {
        if(productRepository.existsByName(input.description())){
            throw new ProductAlreadyExistsException("Já existe um produto cadastrado com esta descrição");
        }
        Product product = new Product(input.description(), input.price());
        Product productSaved = productRepository.save(product);
        return ProductOutput.from(productSaved);
    }
}
