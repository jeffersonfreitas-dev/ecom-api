package dev.jeffersonfreitas.ecom_api.infra.out.persistence.customer;

import dev.jeffersonfreitas.ecom_api.domain.model.Customer;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Email;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Identity;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Name;
import org.springframework.stereotype.Component;

@Component
public final class CustomerMapper {


    public static Customer toDomain(CustomerJpaEntity entity) {
        return new Customer(
                new Identity(entity.getId()),
                new Name(entity.getName()),
                new Email(entity.getEmail()),
                entity.getCreatedAt()
        );
    }

    public static CustomerJpaEntity toEntity(Customer customer) {
       return new CustomerJpaEntity(
            customer.getUuid().value(),
            customer.getName().value(),
            customer.getEmail().value(),
            customer.getCreatedAt()
        );
    }
}
