package com.example.demo.servicio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Mascota;
import com.example.demo.repositorio.MascotaRepository;

@Service
public class MascotaServiceImpl implements MascotaService{
    
    @Autowired
    MascotaRepository repo;

    @Override
    public Mascota SearchById(int id) {
        return repo.findById(id);
    }

    @Override
    public Collection<Mascota> SearchAll(){
        return repo.findAll();
    }
}
