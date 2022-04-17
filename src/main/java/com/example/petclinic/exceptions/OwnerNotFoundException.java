package com.example.petclinic.exceptions;

public class OwnerNotFoundException extends RuntimeException{

    public OwnerNotFoundException(String message){
        super(message);
    }
}
