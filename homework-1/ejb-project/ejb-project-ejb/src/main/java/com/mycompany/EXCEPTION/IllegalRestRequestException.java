package com.mycompany.EXCEPTION;

public class IllegalRestRequestException extends RuntimeException{

    public IllegalRestRequestException(){
        super();
    }
    
    public IllegalRestRequestException(String message){
        super(message);
    }
}
