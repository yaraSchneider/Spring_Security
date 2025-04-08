package com.example.security.security.model.Exeption;

public class CookieNotFoundException extends RuntimeException{

    public CookieNotFoundException(String name){
        super("o cookie de nome" + name + "não foi encontrado");
    }
}
