package com.mycompany.exceptions;

public class IllegalRestRequestException extends RuntimeException{

    public IllegalRestRequestException(){
        super();
    }
    
    public IllegalRestRequestException(String message){
        super(message);
    }
}
