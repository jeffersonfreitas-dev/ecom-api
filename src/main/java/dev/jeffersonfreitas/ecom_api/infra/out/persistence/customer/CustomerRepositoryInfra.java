package dev.jeffersonfreitas.ecom_api.infra.out.persistence.customer;

import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequestInput;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.CustomerPage;
import dev.jeffersonfreitas.ecom_api.application.port.in.customer.getAll.CustomerFilter;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.Customer;
import dev.jeffersonfreitas.ecom_api.infra.out.PageRequestMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public CustomerPage findAll(CustomerFilter filter, PageableRequestInput pageableInput) {
        Pageable pageable = PageRequestMapper.toSpring(pageableInput);
        Specification<CustomerJpaEntity> entitySpecification = CustomerSpecifications.from(filter);
        Page<CustomerJpaEntity> customers = customerJpaRepository.findAll(entitySpecification, pageable);
        return CustomerPageMapper.from(customers);
    }
}
