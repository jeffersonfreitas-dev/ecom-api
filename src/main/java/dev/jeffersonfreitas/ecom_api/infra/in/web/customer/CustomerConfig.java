package dev.jeffersonfreitas.ecom_api.infra.in.web.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CreateCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.application.service.customer.CreateCustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    CreateCustomerUseCase createCustomerUseCase(CustomerRepository customerRepository){
        return new CreateCustomerService(customerRepository);
    }

}
