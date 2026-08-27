package dev.jeffersonfreitas.ecom_api.application.service.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.create.CreateCustomerInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.create.CreateCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerOutput;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.domain.exception.CustomerAlreadyExistsException;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;

public class CreateCustomerService implements CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public CreateCustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerOutput execute(CreateCustomerInput input) {
        if(customerRepository.existsByEmail(input.email())){
            throw new CustomerAlreadyExistsException("E-mail já cadastrado");
        }

        Customer customer = new Customer(input.name(), input.email());
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerOutput.from(savedCustomer);
    }
}
