package dev.jeffersonfreitas.ecom_api.application.service.product;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.GetAllProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductOutput;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;

public class GetAllProductService implements GetAllProductUseCase {

    private final ProductRepository repository;

    public GetAllProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public PageGeneric<ProductOutput> execute(ProductFilter filter, PageableRequestInput pageable) {
        return null;
    }
}
