package dev.jeffersonfreitas.ecom_api.domain.model;

import dev.jeffersonfreitas.ecom_api.domain.valueobject.Email;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Identity;
import dev.jeffersonfreitas.ecom_api.domain.valueobject.Name;

import java.time.Instant;

public class Customer {
    private final Identity uuid;
    private final Name name;
    private final Email email;
    private final Instant createdAt;

    public Customer(String name, String email){
        this.uuid = new Identity();
        this.name = new Name(name);
        this.email = new Email(email);
        this.createdAt = Instant.now();
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Identity getUuid() {
        return uuid;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
