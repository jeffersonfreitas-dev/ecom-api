package dev.jeffersonfreitas.ecom_api.application.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String msg){
        super(msg);
    }
}
