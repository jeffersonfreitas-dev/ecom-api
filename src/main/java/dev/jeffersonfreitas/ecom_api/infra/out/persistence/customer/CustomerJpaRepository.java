package dev.jeffersonfreitas.ecom_api.infra.out.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerJpaRepository extends
        JpaRepository<CustomerJpaEntity, String>,
        JpaSpecificationExecutor<CustomerJpaEntity> {
    boolean existsByEmail(String email);
}
