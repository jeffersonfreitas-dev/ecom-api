package dev.jeffersonfreitas.ecom_api.application.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String msg){
        super(msg);
    }
}
