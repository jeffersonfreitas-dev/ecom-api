package dev.jeffersonfreitas.ecom_api.application.service.order;

import dev.jeffersonfreitas.ecom_api.application.exception.BusinessException;
import dev.jeffersonfreitas.ecom_api.application.exception.OrderNotFoundException;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.DeleteOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.order.OrderRepository;

public class DeleteOrderService implements DeleteOrderUseCase{

    private final OrderRepository repository;

    public DeleteOrderService(OrderRepository repository){
        this.repository = repository;
    }

    @Override
    public void execute(String id) {
        if (id == null || id.isBlank()){
            throw new BusinessException("O código não pode ser nulo ou vazio ao deletar");
        }
        repository.get(id).orElseThrow(() -> new OrderNotFoundException("O pedido não foi encontrado para realizar a exclusão"));
        repository.delete(id);
    }

}
