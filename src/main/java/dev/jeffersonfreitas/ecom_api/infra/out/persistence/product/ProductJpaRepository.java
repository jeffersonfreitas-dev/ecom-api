package dev.jeffersonfreitas.ecom_api.infra.out.persistence.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, String> {
    boolean existsByName(String name);
}
