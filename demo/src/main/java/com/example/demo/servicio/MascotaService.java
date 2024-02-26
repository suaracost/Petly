package com.example.demo.servicio;

import java.util.Collection;

import com.example.demo.entidad.Mascota;

public interface MascotaService {

    public Mascota SearchById(int id);

    public Collection<Mascota> SearchAll();

    public void deleteById(int id);

    public void update(Mascota mascota);

    public void add(Mascota mascota);
    
}
