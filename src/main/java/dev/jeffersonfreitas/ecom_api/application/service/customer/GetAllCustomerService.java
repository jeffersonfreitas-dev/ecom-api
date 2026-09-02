package dev.jeffersonfreitas.ecom_api.application.service.customer;

import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerPage;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.getAll.CustomerFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.getAll.GetAllCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;

public class GetAllCustomerService implements GetAllCustomerUseCase {

    private final CustomerRepository customerRepository;

    public GetAllCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerPage execute(CustomerFilter filter, PageableRequestInput pageableInput) {
        return customerRepository.findAll(filter, pageableInput);
    }
}
