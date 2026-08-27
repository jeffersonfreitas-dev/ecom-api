package dev.jeffersonfreitas.ecom_api.application.port.in.customer.get;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerOutput;

public interface GetCustomerUseCase {

    CustomerOutput execute(String id);
}
