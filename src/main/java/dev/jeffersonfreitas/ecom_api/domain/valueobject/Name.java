package dev.jeffersonfreitas.ecom_api.domain.valueobject;

import dev.jeffersonfreitas.ecom_api.domain.exception.InvalidValueObjectException;

public final class Name {

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    private void validate(String name) {
        if(name == null || name.isBlank()){
            throw new InvalidValueObjectException("O nome informado não pode ser nulo ou vazio");
        }

        if (name.length() < 3 || name.length() > 60){
            throw new InvalidValueObjectException("O nome deve conter no mínimo 3 e máximo 60 caracteres");
        }
    }
}
