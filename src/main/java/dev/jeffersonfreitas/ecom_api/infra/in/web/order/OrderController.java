package dev.jeffersonfreitas.ecom_api.infra.in.web.order;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jeffersonfreitas.ecom_api.application.port.in.order.CreateOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.CreateOrderInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderOutput;

@RestController 
@RequestMapping ("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase){
        this.createOrderUseCase = createOrderUseCase;
    }


    @PostMapping 
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest request){
        CreateOrderInput input = CreateOrderInput.from(request);
        OrderOutput output = createOrderUseCase.execute(input);
        OrderResponse response = OrderResponse.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
