package dev.jeffersonfreitas.ecom_api.application.service.customer;

import dev.jeffersonfreitas.ecom_api.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.UpdateCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerOutput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.UpdateCustomerInput;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Email;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Name;

public class UpdateCustomerService implements UpdateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public UpdateCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerOutput execute(String id, UpdateCustomerInput input) {
        Customer customer = customerRepository.getById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado para realizar a atualização"));
        Name name = new Name(input.name());
        Email email = new Email(input.email());
        Customer customerUpdated = new Customer(customer.getUuid(), name, email, customer.getCreatedAt());
        customerRepository.save(customerUpdated);
        return CustomerOutput.from(customerUpdated);
    }
}
