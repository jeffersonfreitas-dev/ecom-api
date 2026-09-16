package dev.jeffersonfreitas.ecom_api.infra.in.web.order;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jeffersonfreitas.ecom_api.application.port.in.order.CreateOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.DeleteOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.GetOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.CreateOrderInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderOutput;

@RestController 
@RequestMapping ("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase,
                            DeleteOrderUseCase deleteOrderUseCase){
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
    }


    @PostMapping 
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest request){
        CreateOrderInput input = CreateOrderInput.from(request);
        OrderOutput output = createOrderUseCase.execute(input);
        OrderResponse response = OrderResponse.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> get(@PathVariable String id){
        OrderOutput output = getOrderUseCase.execute(id);
        OrderResponse response = OrderResponse.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        deleteOrderUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
