package dev.jeffersonfreitas.ecom_api.application.port.in.customer;

public interface GetCustomerUseCase {

    CustomerOutput execute(String id);
}
