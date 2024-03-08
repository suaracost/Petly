package com.example.demo.servicio;

import java.util.Collection;

import com.example.demo.entidad.Droga;

public interface DrogaService {
    
    public Droga SearchById(Long id);

    public Collection<Droga> SearchAll();

    public void deleteById(Long id);

    public void update(Droga droga);

    public void add(Droga droga);
}
