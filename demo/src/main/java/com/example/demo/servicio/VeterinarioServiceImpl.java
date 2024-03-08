package com.example.demo.servicio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;

@Service
public class VeterinarioServiceImpl implements VeterinarioService{

    @Autowired
    VeterinarioRepository repo;

    public Veterinario SearchById(Long id) {
        return repo.findById(id).get();
    };

    public Collection<Veterinario> SearchAll() {
        return repo.findAll();
    };

    public void deleteById(Long id) {
        repo.deleteById(id);
    };

    public void update(Veterinario veterinario) {
        repo.save(veterinario);
    };

    public void add(Veterinario veterinario) {
        repo.save(veterinario);
    };
}
