package dev.jeffersonfreitas.ecom_api.infra.in.web.product;

import dev.jeffersonfreitas.ecom_api.application.port.in.product.CreateProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.application.service.product.CreateProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    CreateProductUseCase createProductUseCase(ProductRepository productRepository){
        return new CreateProductService(productRepository);
    }
}
