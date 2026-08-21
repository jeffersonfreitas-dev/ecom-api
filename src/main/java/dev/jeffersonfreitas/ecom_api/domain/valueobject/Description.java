package dev.jeffersonfreitas.ecom_api.domain.valueobject;

import dev.jeffersonfreitas.ecom_api.domain.exception.InvalidValueObjectException;

public final class Description {

    private final String description;

    public Description(String description) {
        validate(description);
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    private void validate(String description) {
        if(description == null || description.isBlank()){
            throw new InvalidValueObjectException("A descrição informado não pode ser nulo ou vazio");
        }

        if (description.length() < 3 || description.length() > 300){
            throw new InvalidValueObjectException("A descrição deve conter no mínimo 3 e máximo 300 caracteres");
        }
    }
}
