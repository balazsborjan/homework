package com.mycompany.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class IllegalRestRequestException extends RuntimeException{

    public IllegalRestRequestException(){
        super();
    }
    
    public IllegalRestRequestException(String message){
        super(message);
    }
}
