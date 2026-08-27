package dev.jeffersonfreitas.ecom_api.infra.out.persistence.customer;

import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        CustomerJpaEntity entity = CustomerMapper.toEntity(customer);
        entity = customerJpaRepository.save(entity);
        return CustomerMapper.toDomain(entity);
    }

    @Override
    public Optional<Customer> getById(String id) {
        return customerJpaRepository.findById(id).map(CustomerMapper::toDomain);
    }
}
