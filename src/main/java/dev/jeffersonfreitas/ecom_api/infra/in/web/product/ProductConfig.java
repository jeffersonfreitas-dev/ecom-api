package dev.jeffersonfreitas.ecom_api.infra.in.web.product;

import dev.jeffersonfreitas.ecom_api.application.port.in.product.CreateProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.DeleteProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.GetAllProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.product.GetProductUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.application.service.product.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    CreateProductUseCase createProductUseCase(ProductRepository productRepository){
        return new CreateProductService(productRepository);
    }

    @Bean
    GetProductUseCase getProductUseCase(ProductRepository productRepository){
        return new GetProductService(productRepository);
    }

    @Bean
    DeleteProductUseCase deleteProductUseCase(ProductRepository productRepository){
        return new DeleteProductService(productRepository);
    }

    @Bean
    GetAllProductUseCase getAllProductUseCase(ProductRepository productRepository){
        return new GetAllProductService(productRepository);
    }

    @Bean
    UpdateProductService updateProductService(ProductRepository productRepository){
        return new UpdateProductService(productRepository);
    }
}
