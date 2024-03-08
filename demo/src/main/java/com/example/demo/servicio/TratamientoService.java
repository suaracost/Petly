package com.example.demo.servicio;

import java.util.Collection;

import com.example.demo.entidad.Tratamiento;

public interface TratamientoService {
    
    public Tratamiento SearchById(Long id);

    public Collection<Tratamiento> SearchAll();

    public void deleteById(Long id);

    public void update(Tratamiento tratamiento);

    public void add(Tratamiento tratamiento);
}
