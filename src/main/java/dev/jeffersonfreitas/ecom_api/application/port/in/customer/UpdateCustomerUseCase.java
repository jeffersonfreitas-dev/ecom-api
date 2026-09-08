package dev.jeffersonfreitas.ecom_api.application.port.in.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerOutput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.UpdateCustomerInput;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;

public interface UpdateCustomerUseCase {

    CustomerOutput execute(String id, UpdateCustomerInput input);
}
