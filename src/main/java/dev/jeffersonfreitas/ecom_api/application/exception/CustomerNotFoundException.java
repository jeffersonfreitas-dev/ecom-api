package dev.jeffersonfreitas.ecom_api.application.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String msg){
        super(msg);
    }
}
