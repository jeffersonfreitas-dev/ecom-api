package dev.jeffersonfreitas.ecom_api.infra.in.web.product;

import dev.jeffersonfreitas.ecom_api.application.port.in.product.CreateProductInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.CreateProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.ProductOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping
    private ResponseEntity<ProductResponse> create(@RequestBody CreateProductRequest request){
        CreateProductInput input = new CreateProductInput(request.name(), request.description(), request.price());
        ProductOutput output = createProductUseCase.execute(input);
        ProductResponse response = ProductResponse.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
