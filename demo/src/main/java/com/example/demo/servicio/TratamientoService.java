package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidad.Tratamiento;

public interface TratamientoService {
    
    public Tratamiento SearchById(Long id);

    public List<Tratamiento> SearchAll();

    public void deleteById(Long id);

    public void update(Tratamiento tratamiento);

    public void add(Tratamiento tratamiento);

    public List<Tratamiento> findByMascotaTId(Long id);

    public List<Tratamiento> findByVeterinarioTId(Long id);
}
