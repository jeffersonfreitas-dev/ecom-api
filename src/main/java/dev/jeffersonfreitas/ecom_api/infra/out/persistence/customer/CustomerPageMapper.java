package dev.jeffersonfreitas.ecom_api.infra.out.persistence.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerPage;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public final class CustomerPageMapper {

    public static CustomerPage from(Page<CustomerJpaEntity> entities){
        List<Customer> customers = entities.stream().map(CustomerMapper::toDomain).toList();
        return new CustomerPage(
                customers,
                entities.getNumber(),
                entities.getSize(),
                entities.getNumberOfElements(),
                entities.getTotalPages()
        );
    }
}
