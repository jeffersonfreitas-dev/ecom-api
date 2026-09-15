package dev.jeffersonfreitas.ecom_api.application.service.order;

import dev.jeffersonfreitas.ecom_api.application.exception.BusinessException;
import dev.jeffersonfreitas.ecom_api.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.ecom_api.application.exception.ProductNotFoundException;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.CreateOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.CreateOrderInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.CreateOrderItemInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderOutput;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.order.Order;
import dev.jeffersonfreitas.ecom_api.domain.model.order.OrderItem;

import java.util.List;

public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepository repository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public CreateOrderService(OrderRepository repository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderOutput execute(CreateOrderInput input) {
        if(input == null){
            throw new BusinessException("O input de pedido não pode ser nulo");
        }

        customerRepository.getById(input.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("Não foi encontrado o cliente com o código informado"));

        List<OrderItem> items = addItems(input.itemInputs());
        Order order = new Order(input.customerId(), items);
        order = repository.save(order);
        return OrderOutput.from(order);
    }

    private List<OrderItem> addItems(List<CreateOrderItemInput> inputs) {
        if(inputs == null || inputs.isEmpty()){
            throw new BusinessException("Não foi possível salvar o pedido. Itens nulos ou vazios");
        }

        inputs.forEach(item -> productRepository.get(item.productId())
                .orElseThrow(() -> new ProductNotFoundException("Não foi encontrado o produto com o código informado")));

        return inputs.stream().map(
                item -> new OrderItem(item.productId(), item.quantity(), item.value())).toList();

    }
}
