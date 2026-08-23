package dev.jeffersonfreitas.ecom_api.domain.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException(String msg){
        super(msg);
    }
}
