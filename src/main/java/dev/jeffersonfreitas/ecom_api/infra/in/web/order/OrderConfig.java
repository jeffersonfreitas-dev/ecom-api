package dev.jeffersonfreitas.ecom_api.infra.in.web.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.jeffersonfreitas.ecom_api.application.port.in.order.CreateOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.application.service.order.CreateOrderService;

@Configuration 
public class OrderConfig {

    @Bean 
    CreateOrderUseCase createOrderUseCase(OrderRepository repository, CustomerRepository customerRepository, ProductRepository productRepository){
        return new CreateOrderService(repository, customerRepository, productRepository);
    }
}
