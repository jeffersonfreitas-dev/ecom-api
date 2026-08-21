package dev.jeffersonfreitas.ecom_api.domain.exception;

public class InvalidValueObjectException extends RuntimeException{

    public InvalidValueObjectException(String msg){
        super(msg);
    }
}
