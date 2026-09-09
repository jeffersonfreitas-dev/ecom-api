package dev.jeffersonfreitas.ecom_api.application.port.in.product;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductOutput;

public interface GetAllProductUseCase {
    PageGeneric<ProductOutput> execute(ProductFilter filter, PageableRequestInput pageable);
}
