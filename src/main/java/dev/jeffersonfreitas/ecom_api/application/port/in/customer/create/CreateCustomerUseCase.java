package dev.jeffersonfreitas.ecom_api.application.port.in.customer.create;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerOutput;

public interface CreateCustomerUseCase {

    CustomerOutput execute(CreateCustomerInput input);
}
