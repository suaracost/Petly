package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Role;
import com.example.demo.entidad.UserEntity;
import com.example.demo.repositorio.UserRepository;

@Service
public class RolServiceImpl implements RolService{

    @Autowired
    UserRepository userRepository;
    
    public Role findRoleByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user != null && !user.getRoles().isEmpty()) {
            return user.getRoles().get(0); // Asumiendo que el usuario tiene un solo rol
        }
        return null;
    }
    
}
