package com.example.demo.controlador;

public class IdClienteNotFoundException extends RuntimeException{
    private Long id;

    public IdClienteNotFoundException(Long id){
        this.id = id;
    }

    public Long getid(){
        return id;
    }

    public void setid(Long id){
        this.id = id;
    }
    
}
