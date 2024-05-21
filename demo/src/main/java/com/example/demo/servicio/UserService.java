package com.example.demo.servicio;

import com.example.demo.entidad.UserEntity;

public interface UserService {

    public UserEntity findByUsername(String cedula);
    
}
