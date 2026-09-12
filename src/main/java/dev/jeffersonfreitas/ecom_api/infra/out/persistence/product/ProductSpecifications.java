package dev.jeffersonfreitas.ecom_api.infra.out.persistence.product;

import dev.jeffersonfreitas.ecom_api.application.port.in.product.dto.ProductFilter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {

    public static Specification<ProductJpaEntity> from(ProductFilter filter){
        Specification<ProductJpaEntity> specification = null;

        if(filter.description() != null && !filter.description().isBlank()){
            specification = and(specification, descriptionContains(filter.description()));
        }

        if(filter.initPrice() != null){
            specification = and(specification, initValueGreaterThan(filter.initPrice()));
        }

        if(filter.finalPrice() != null){
            specification = and(specification, finalValueLessThan(filter.finalPrice()));
        }

        return specification;
    }

    private static Specification<ProductJpaEntity> and(Specification<ProductJpaEntity> current, Specification<ProductJpaEntity> next){
        return current == null ? next : current.and(next);
    }

    private static Specification<ProductJpaEntity> descriptionContains(String description){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    private static Specification<ProductJpaEntity> initValueGreaterThan(BigDecimal initPrice){
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(root.get("price"), initPrice);
    }

    private static Specification<ProductJpaEntity> finalValueLessThan(BigDecimal finalPrice){
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(root.get("price"), finalPrice);
    }
}
