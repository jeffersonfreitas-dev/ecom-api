package dev.jeffersonfreitas.ecom_api.infra.in.web.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.GetCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.create.CreateCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.getAll.GetAllCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.application.service.customer.CreateCustomerService;
import dev.jeffersonfreitas.ecom_api.application.service.customer.GetAllCustomerService;
import dev.jeffersonfreitas.ecom_api.application.service.customer.GetCustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    CreateCustomerUseCase createCustomerUseCase(CustomerRepository customerRepository){
        return new CreateCustomerService(customerRepository);
    }

    @Bean
    GetCustomerUseCase getCustomerUseCase(CustomerRepository customerRepository){
        return new GetCustomerService(customerRepository);
    }

    @Bean
    GetAllCustomerUseCase getAllCustomerUseCase(CustomerRepository customerRepository){
        return new GetAllCustomerService(customerRepository);
    }

}
