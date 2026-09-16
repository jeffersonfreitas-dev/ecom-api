package dev.jeffersonfreitas.ecom_api.application.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String msg){
        super(msg);
    }
}
