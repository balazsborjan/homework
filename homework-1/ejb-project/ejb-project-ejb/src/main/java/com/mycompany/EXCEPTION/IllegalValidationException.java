package com.mycompany.EXCEPTION;

public class IllegalValidationException extends RuntimeException{

    public IllegalValidationException(){
        super();
    }
    
    public IllegalValidationException(String message){
        super(message);
    }
}
