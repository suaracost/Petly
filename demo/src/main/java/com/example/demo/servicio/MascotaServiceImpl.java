package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Mascota;
import com.example.demo.repositorio.MascotaRepository;

@Service
public class MascotaServiceImpl implements MascotaService{
    
    @Autowired
    MascotaRepository repo;

    @Override
    public Mascota SearchById(Long id) {
        Optional<Mascota> optionalMascota = repo.findById(id);
        return optionalMascota.orElse(null);
    }

    @Override
    public List<Mascota> SearchAll(){
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Mascota mascota) {
        repo.save(mascota);
    }

    @Override
    public void add(Mascota mascota) {
        repo.save(mascota);
    }

    @Override
    public void updateState(Long id) {
        //Hacer un update de la mascota con el id que se recibe y cambiar el estado, de disponible a inactivo o viceversa
        Mascota mascota = repo.findById(id).get();

        if(mascota.getEstado().equals("Disponible"))
            mascota.setEstado("Inactivo");
        else
            mascota.setEstado("Disponible");

        repo.save(mascota);
    }

    @Override
    public Mascota searchByUsuarioId(long id) {
        return repo.findByClienteId(id);
    }

    
}
