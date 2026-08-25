package dev.jeffersonfreitas.ecom_api.domain.model;

import dev.jeffersonfreitas.ecom_api.domain.valueobject.Description;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Identity;

import java.math.BigDecimal;
import java.time.Instant;

public class Product {
    private Identity uuid;
    private Description description;
    private BigDecimal price;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;


    public Product(String description, BigDecimal price){
        this.uuid = new Identity();
        this.description = new Description(description);
        this.price = price;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
        activated();
    }

    public Product(Identity identity, Description description, BigDecimal price, boolean active, Instant createdAt, Instant updatedAt){
        this.uuid = identity;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }

    public void activated(){
        this.active = true;
    }

    public void deactivated(){
        this.active = false;
    }

    public Identity getUuid() {
        return uuid;
    }

    public Description getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public boolean isActive() {
        return active;
    }
}
