package com.example.demo.servicio;

import java.util.Collection;

import com.example.demo.entidad.Mascota;

public interface MascotaService {

    public Mascota SearchById(Long id);

    public Collection<Mascota> SearchAll();

    public void deleteById(Long id);

    public void update(Mascota mascota);

    public void add(Mascota mascota);

    public void updateState(Long id);
    
}
