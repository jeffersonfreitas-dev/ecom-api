package dev.jeffersonfreitas.ecom_api.infra.out.persistence.customer;

import dev.jeffersonfreitas.ecom_api.domain.model.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class CustomerJpaEntity {

    @Id
    private String id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = false)
    private Instant createdAt;

    public static CustomerJpaEntity from(Customer customer) {
        return new CustomerJpaEntity(
                customer.getUuid().value(),
                customer.getName().value(),
                customer.getEmail().value(),
                customer.getCreatedAt()
        );
    }
}
