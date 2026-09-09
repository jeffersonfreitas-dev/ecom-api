package dev.jeffersonfreitas.ecom_api.application.service.product;

import dev.jeffersonfreitas.ecom_api.application.exception.ProductNotFoundException;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.GetProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductOutput;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Product;

public class GetProductService implements GetProductUseCase {

    private final ProductRepository repository;

    public GetProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductOutput execute(String id) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("O código informado não pode ser nulo ou vazio");
        }
        Product product = repository.get(id).orElseThrow(() -> new ProductNotFoundException("Não existe produto com o código informado"));
        return ProductOutput.from(product);
    }
}
