package dev.jeffersonfreitas.ecom_api.application.port.in.customer;

public interface CreateCustomerUseCase {

    CustomerOutput execute(CreateCustomerInput input);
}
