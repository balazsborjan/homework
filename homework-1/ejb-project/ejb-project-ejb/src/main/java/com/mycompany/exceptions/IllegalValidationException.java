package com.mycompany.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class IllegalValidationException extends RuntimeException{

    public IllegalValidationException(){
        super();
    }
    
    public IllegalValidationException(String message){
        super(message);
    }
}
