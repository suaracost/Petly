package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Tratamiento;
import com.example.demo.repositorio.TratamientoRepository;

@Service
public class TratamientoServiceImpl implements TratamientoService{

    @Autowired
    TratamientoRepository repo;

    @Override
    public Tratamiento SearchById(Long id) {
        Optional<Tratamiento> optionalTratamiento = repo.findById(id);
        return optionalTratamiento.orElse(null);
    };

    @Override
    public List<Tratamiento> SearchAll() {
        return repo.findAll();
    };

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    };

    @Override
    public void update(Tratamiento tratamiento) {
        repo.save(tratamiento);
    };

    @Override
    public void add(Tratamiento tratamiento) {
        repo.save(tratamiento);
    }

    @Override
    public List<Tratamiento> findBymascotaId(Long id) {
        return repo.findBymascotaId(id);
    }

    @Override
    public List<Tratamiento> findByveterinarioId(Long id) {
        return repo.findByveterinarioId(id);
    }

    @Override
    public Long countTratamientosMes() {
        return repo.countTratamientosMes();
    }

    @Override
    public List<Object[]> countTratamientosPorDroga() {
        return repo.countTratamientosPorDroga();
    }

    @Override
    public List<Object[]> tratamientosMensualesAnio() { return repo.tratamientosMensualesAnio(); }

    @Override
    public List<Object[]> gananciasMensualesAnio() { return repo.gananciasMensualesAnio(); }
}
