package dev.jeffersonfreitas.ecom_api.application.service.product;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequest;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.GetAllProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductOutput;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Product;

public class GetAllProductService implements GetAllProductUseCase {

    private final ProductRepository repository;

    public GetAllProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public PageGeneric<ProductOutput> execute(ProductFilter filter, PageableRequest pageable) {
        PageGeneric<Product> productPageGeneric = repository.getAll(filter, pageable);
        return productPageGeneric.map(ProductOutput::from);
    }
}
