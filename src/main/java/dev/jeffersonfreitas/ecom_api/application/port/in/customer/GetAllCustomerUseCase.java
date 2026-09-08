package dev.jeffersonfreitas.ecom_api.application.port.in.customer;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerOutput;

public interface GetAllCustomerUseCase {
    PageGeneric<CustomerOutput> execute(CustomerFilter input, PageableRequestInput pageableInput);
}
