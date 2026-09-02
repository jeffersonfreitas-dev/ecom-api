package dev.jeffersonfreitas.ecom_api.infra.in.web.customer;

import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerOutput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerPage;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.GetCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.create.CreateCustomerInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.create.CreateCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.getAll.CustomerFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.getAll.GetAllCustomerUseCase;
import dev.jeffersonfreitas.ecom_api.application.dto.SortOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final GetAllCustomerUseCase getAllCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase, GetCustomerUseCase getCustomerUseCase,
                              GetAllCustomerUseCase getAllCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.getAllCustomerUseCase = getAllCustomerUseCase;
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

    @GetMapping
    public ResponseEntity<CustomerPage> findAll(
            @PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC) Pageable pageable, CustomerFilter filter){

        PageableRequestInput pageableInput = new PageableRequestInput(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort().stream()
                        .map(order -> new SortOrder(
                                order.getProperty(),
                                order.getDirection().name())).toList()
        );
        CustomerPage customerPage = getAllCustomerUseCase.execute(filter, pageableInput);
        return ResponseEntity.status(HttpStatus.OK).body(customerPage);
    }
}
