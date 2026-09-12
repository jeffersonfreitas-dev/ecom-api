package dev.jeffersonfreitas.ecom_api.application.service.product;

import dev.jeffersonfreitas.ecom_api.application.exception.BusinessException;
import dev.jeffersonfreitas.ecom_api.application.exception.ProductNotFoundException;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.DeleteProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;

public class DeleteProductService implements DeleteProductUseCase {

    private final ProductRepository repository;

    public DeleteProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String id) {
        if (id == null || id.isBlank()){
            throw new BusinessException("O código não pode ser nulo ou vazio ao deletar");
        }
        repository.get(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado para exclusão"));
        repository.delete(id);
    }
}
