package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.UserEntity;
import com.example.demo.repositorio.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity findByUsername(String cedula) {
        return userRepository.findByUsername(cedula).orElse(null);
    }
    
}
