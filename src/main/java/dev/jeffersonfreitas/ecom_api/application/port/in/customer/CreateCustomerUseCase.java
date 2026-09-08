package dev.jeffersonfreitas.ecom_api.application.port.in.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CreateCustomerInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerOutput;

public interface CreateCustomerUseCase {

    CustomerOutput execute(CreateCustomerInput input);
}
