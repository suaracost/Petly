package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Veterinario;
import com.example.demo.repositorio.VeterinarioRepository;

@Service
public class VeterinarioServiceImpl implements VeterinarioService{

    @Autowired
    VeterinarioRepository repo;

    @Override
    public Veterinario SearchById(Long id) {
        Optional<Veterinario> optionalVeterinario = repo.findById(id);
        return optionalVeterinario.orElse(null);
    };

    @Override
    public List<Veterinario> SearchAll() {
        return repo.findAll();
    };

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    };

    @Override
    public Veterinario update(Veterinario veterinario) {
        return repo.save(veterinario);
    };

    @Override
    public Veterinario add(Veterinario veterinario) {
        return repo.save(veterinario);
    };

    @Override
    public Veterinario updateState(Long id) {
        //Hacer un update de la mascota con el id que se recibe y cambiar el estado, de disponible a inactivo o viceversa
        Veterinario vet = repo.findById(id).get();

        if(vet.getEstado().equals("Disponible"))
        vet.setEstado("Inactivo");
        else
        vet.setEstado("Disponible");

        return repo.save(vet);
    }

    @Override
    public Long countVeterinarios() {
        return repo.countVeterinarios();
    }

    @Override
    public Long countVeterinariosInactivos() {
        return repo.countVeterinariosInactivos();
    }

    @Override
    public Veterinario SearchByCedula(String cedula) {
        return repo.findByCedula(cedula);
    }

    @Override
    public List<Veterinario> findByEspecialidadNotAdmin() {
        return repo.findByEspecialidadNot("Administrador");
    }
}
