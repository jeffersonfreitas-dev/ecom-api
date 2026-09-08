package dev.jeffersonfreitas.ecom_api.application.service.customer;

import dev.jeffersonfreitas.ecom_api.application.exception.BusinessException;
import dev.jeffersonfreitas.ecom_api.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.DeleteCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;

public class DeleteCustomerService implements DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    public DeleteCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void execute(String id) {
        if (id == null || id.isBlank()){
            throw new BusinessException("O código não pode ser nulo ou vazio ao deletar");
        }
        customerRepository.getById(id).orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado para exclusão"));
        customerRepository.delete(id);
    }
}
