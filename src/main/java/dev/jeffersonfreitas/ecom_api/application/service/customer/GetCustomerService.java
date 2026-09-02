package dev.jeffersonfreitas.ecom_api.application.service.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerOutput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.GetCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;

public class GetCustomerService implements GetCustomerUseCase {

    private final CustomerRepository customerRepository;

    public GetCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerOutput execute(String id) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("O código informado não pode ser nulo ou vazio");
        }
        Customer customer = customerRepository.getById(id).orElseThrow(
                () -> new CustomerNotFoundException("Não existe cliente com o código informado"));
        return CustomerOutput.from(customer);
    }
}
