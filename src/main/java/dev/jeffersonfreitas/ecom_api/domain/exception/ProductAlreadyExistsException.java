package dev.jeffersonfreitas.ecom_api.domain.exception;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException(String msg){
        super(msg);
    }
}
