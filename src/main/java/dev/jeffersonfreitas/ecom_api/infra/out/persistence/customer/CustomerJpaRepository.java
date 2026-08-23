package dev.jeffersonfreitas.ecom_api.infra.out.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, String> {
    boolean existsByEmail(String email);
}
