package dev.jeffersonfreitas.ecom_api.application.service.order;

import dev.jeffersonfreitas.ecom_api.application.exception.BusinessException;
import dev.jeffersonfreitas.ecom_api.application.exception.OrderNotFoundException;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.GetOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderOutput;
import dev.jeffersonfreitas.ecom_api.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.order.Order;

public class GetOrderService implements GetOrderUseCase{

    private final OrderRepository repository;

    public GetOrderService(OrderRepository repository){
        this.repository = repository;
    }

    @Override
    public OrderOutput execute(String id) {
        if(id == null || id.isBlank()){
            throw new BusinessException("O código não pode ser nulo ou vazio");
        }

        Order order = repository.get(id)
            .orElseThrow(() -> new OrderNotFoundException("Não foi encontrado o pedido com este código"));
        return OrderOutput.from(order);
    }

}
