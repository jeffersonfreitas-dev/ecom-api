package dev.jeffersonfreitas.ecom_api.application.port.in.customer.getAll;

import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerPage;

public interface GetAllCustomerUseCase {
    CustomerPage execute(CustomerFilter input, PageableRequestInput pageableInput);
}
