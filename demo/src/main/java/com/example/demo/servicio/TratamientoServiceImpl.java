package com.example.demo.servicio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Tratamiento;
import com.example.demo.repositorio.TratamientoRepository;

@Service
public class TratamientoServiceImpl implements TratamientoService{

    @Autowired
    TratamientoRepository repo;

    public Tratamiento SearchById(Long id) {
        return repo.findById(id).get();
    };

    public Collection<Tratamiento> SearchAll() {
        return repo.findAll();
    };

    public void deleteById(Long id) {
        repo.deleteById(id);
    };

    public void update(Tratamiento tratamiento) {
        repo.save(tratamiento);
    };

    public void add(Tratamiento tratamiento) {
        repo.save(tratamiento);
    };
}
