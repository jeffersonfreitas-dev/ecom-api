package dev.jeffersonfreitas.ecom_api.infra.in.web.product;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequest;
import dev.jeffersonfreitas.ecom_api.application.dto.SortOrder;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.*;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.CreateProductInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductOutput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.UpdateProductInput;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final GetAllProductUseCase getAllProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, GetProductUseCase getProductUseCase,
                             DeleteProductUseCase deleteProductUseCase, GetAllProductUseCase getAllProductUseCase,
                             UpdateProductUseCase updateProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.getAllProductUseCase = getAllProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody CreateProductRequest request){
        CreateProductInput input = new CreateProductInput(request.description(), request.price());
        ProductOutput output = createProductUseCase.execute(input);
        ProductResponse response = ProductResponse.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable String id){
        ProductOutput output = getProductUseCase.execute(id);
        ProductResponse response = ProductResponse.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<PageGeneric<ProductResponse>> getAll(@PageableDefault(size = 20, sort = "description", direction = Sort.Direction.ASC)
                                                                   Pageable pageable, ProductFilter filter){
        List<SortOrder> sort = pageable.getSort().stream().map(o -> new SortOrder(o.getProperty(), o.getDirection().name())).toList();
        PageableRequest pageableRequest = PageableRequest.create(pageable.getPageNumber(), pageable.getPageSize(), sort);
        PageGeneric<ProductOutput> productOutput = getAllProductUseCase.execute(filter, pageableRequest);
        PageGeneric<ProductResponse> response = productOutput.map(ProductResponse::from);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        deleteProductUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable String id, @RequestBody UpdateProductRequest request){
        UpdateProductInput input = new UpdateProductInput(request.description());
        ProductOutput output = updateProductUseCase.execute(id, input);
        ProductResponse response = ProductResponse.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
