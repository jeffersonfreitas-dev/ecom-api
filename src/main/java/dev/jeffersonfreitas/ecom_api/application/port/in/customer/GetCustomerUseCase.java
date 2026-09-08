package dev.jeffersonfreitas.ecom_api.application.port.in.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerOutput;

public interface GetCustomerUseCase {

    CustomerOutput execute(String id);
}
