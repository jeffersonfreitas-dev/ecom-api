package dev.jeffersonfreitas.ecom_api.domain.valueobject;

import dev.jeffersonfreitas.ecom_api.domain.exception.InvalidValueObjectException;

public final class OrderQuantity {
    private final double quantity;

    public OrderQuantity(double quantity){
        validate(quantity);
        this.quantity = quantity;
    }

    public double value(){
        return this.quantity;
    }

    private void validate(double quantity){
        if (quantity <= 0.0 ){
            throw new InvalidValueObjectException("O valor informado não pode ser nulo ou menor/igual a zero");
        }
    }

}
