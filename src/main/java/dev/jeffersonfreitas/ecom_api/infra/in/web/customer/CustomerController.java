package dev.jeffersonfreitas.ecom_api.infra.in.web.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CreateCustomerInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CreateCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CreateCustomerRequest request){
        CreateCustomerInput input = new CreateCustomerInput(request.name(), request.email());
        CustomerOutput output = createCustomerUseCase.execute(input);
        CustomerResponse response = CustomerResponse.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
