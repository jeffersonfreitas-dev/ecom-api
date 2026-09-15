package dev.jeffersonfreitas.ecom_api.infra.out.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, String> {
}
