package dev.jeffersonfreitas.ecom_api.infra.out.persistence.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, String>, JpaSpecificationExecutor<ProductJpaEntity> {
    boolean existsByDescription(String description);
}
