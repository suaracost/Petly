package com.example.demo.controlador;

public class IdClienteNotFoundException extends RuntimeException{
    private Integer id;

    public IdClienteNotFoundException(Integer id){
        this.id = id;
    }

    public Integer getid(){
        return id;
    }

    public void setid(Integer id){
        this.id = id;
    }
    
}
