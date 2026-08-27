package dev.jeffersonfreitas.ecom_api.infra.in.web.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.create.CreateCustomerInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.create.CreateCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerOutput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.get.GetCustomerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase, GetCustomerUseCase getCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CreateCustomerRequest request){
        CreateCustomerInput input = new CreateCustomerInput(request.name(), request.email());
        CustomerOutput output = createCustomerUseCase.execute(input);
        CustomerResponse response = CustomerResponse.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable String id){
        CustomerOutput output = getCustomerUseCase.execute(id);
        CustomerResponse response = CustomerResponse.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
