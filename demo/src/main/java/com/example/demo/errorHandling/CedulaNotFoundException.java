package com.example.demo.errorHandling;

public class CedulaNotFoundException extends RuntimeException{
    private String cedula;

    public CedulaNotFoundException(String cedula){
        this.cedula = cedula;
    }

    public String getCedula(){
        return cedula;
    }

    public void setCedula(String cedula){
        this.cedula = cedula;
    }
    
}
