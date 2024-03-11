package com.example.demo.servicio;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;

@Service
public class VeterinarioServiceImpl implements VeterinarioService{

    @Autowired
    VeterinarioRepository repo;

    public Veterinario SearchById(Long id) {
        Optional<Veterinario> optionalVeterinario = repo.findById(id);
        return optionalVeterinario.orElse(null);
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
