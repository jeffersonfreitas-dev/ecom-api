package dev.jeffersonfreitas.ecom_api.infra.out.persistence.customer;

import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Email;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Identity;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Name;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryInfra implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerRepositoryInfra(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity entity = CustomerJpaEntity.from(customer);
        entity = customerJpaRepository.save(entity);
        return new Customer(
                new Identity(entity.getId()),
                new Name(entity.getName()),
                new Email(entity.getEmail()),
                entity.getCreatedAt()
        );
    }
}
