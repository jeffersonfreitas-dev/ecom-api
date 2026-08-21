package dev.jeffersonfreitas.ecom_api.domain.valueobject;

import dev.jeffersonfreitas.ecom_api.domain.exception.InvalidValueObjectException;

import java.util.UUID;

public final class Identity {
    private final String uuid;

    public Identity(){
        this.uuid = UUID.randomUUID().toString();
    }

    public Identity(String uuid){
        try{
            UUID.fromString(uuid);
            this.uuid = uuid;
        }catch (IllegalArgumentException err){
            throw new InvalidValueObjectException("O código informado é inválido");
        }
    }

    public String getIdentity(){
        return this.uuid;
    }
}
