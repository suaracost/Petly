package com.example.demo.servicio;


import com.example.demo.entidad.Role;

public interface RolService {

    public Role findRoleByUserId(Long userId);
    
}
