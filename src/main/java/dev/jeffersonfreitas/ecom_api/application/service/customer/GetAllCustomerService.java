package dev.jeffersonfreitas.ecom_api.application.service.customer;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequest;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.GetAllCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerOutput;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;

public class GetAllCustomerService implements GetAllCustomerUseCase {

    private final CustomerRepository customerRepository;

    public GetAllCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public PageGeneric<CustomerOutput> execute(CustomerFilter filter, PageableRequest pageableInput) {
        PageGeneric<Customer> customerPageGeneric = customerRepository.findAll(filter, pageableInput);
        return customerPageGeneric.map(CustomerOutput::from);
    }
}
