package dev.jeffersonfreitas.ecom_api.infra.out.persistence.customer;

import dev.jeffersonfreitas.ecom_api.application.port.in.customer.getAll.CustomerFilter;
import org.springframework.data.jpa.domain.Specification;

public final class CustomerSpecifications {

    public static Specification<CustomerJpaEntity> from(CustomerFilter filter){
        Specification<CustomerJpaEntity> specification = null;

        if(filter.name() != null && !filter.name().isBlank()){
            specification = and(specification, nameContains(filter.name()));
        }

        return specification;
    }

    private static Specification<CustomerJpaEntity> and(Specification<CustomerJpaEntity> current,
                                                        Specification<CustomerJpaEntity> next) {
        return current == null ? next : current.and(next);
    }

    private static Specification<CustomerJpaEntity> nameContains(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
}
