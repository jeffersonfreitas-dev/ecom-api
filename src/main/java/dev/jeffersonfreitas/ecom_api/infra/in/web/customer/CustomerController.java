package dev.jeffersonfreitas.ecom_api.infra.in.web.customer;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequest;
import dev.jeffersonfreitas.ecom_api.application.dto.SortOrder;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.*;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CreateCustomerInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.CustomerOutput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.dto.UpdateCustomerInput;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final GetAllCustomerUseCase getAllCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase, GetCustomerUseCase getCustomerUseCase,
                              GetAllCustomerUseCase getAllCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase,
                              UpdateCustomerUseCase updateCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.getAllCustomerUseCase = getAllCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CreateCustomerRequest request){
        CreateCustomerInput input = new CreateCustomerInput(request.name(), request.email());
        CustomerOutput output = createCustomerUseCase.execute(input);
        CustomerResponse response = CustomerResponse.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable String id, @RequestBody UpdateCustomerRequest request){
        UpdateCustomerInput input = new UpdateCustomerInput(request.name(), request.email());
        CustomerOutput output = updateCustomerUseCase.execute(id, input);
        CustomerResponse response = CustomerResponse.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable String id){
        CustomerOutput output = getCustomerUseCase.execute(id);
        CustomerResponse response = CustomerResponse.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<PageGeneric<CustomerResponse>> getAll(
            @PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC) Pageable pageable, CustomerFilter filter){

        List<SortOrder> sort = pageable.getSort().stream().map(o -> new SortOrder(o.getProperty(), o.getDirection().name())).toList();
        PageableRequest pageableRequest = PageableRequest.create(pageable.getPageNumber(), pageable.getPageSize(), sort);
        PageGeneric<CustomerOutput> customerPage = getAllCustomerUseCase.execute(filter, pageableRequest);
        PageGeneric<CustomerResponse> responsePageGeneric = customerPage.map(CustomerResponse::from);
        return ResponseEntity.status(HttpStatus.OK).body(responsePageGeneric);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        deleteCustomerUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
