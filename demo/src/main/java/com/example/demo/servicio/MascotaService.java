package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidad.Mascota;

public interface MascotaService {

    public Mascota SearchById(Long id);

    public List<Mascota> SearchAll();

    public void deleteById(Long id);

    public void update(Mascota mascota);

    public void add(Mascota mascota);

    public void updateState(Long id);

    public Mascota searchByUsuarioId(long id);

    //Dashboard - 5
    public Long countMascotas();

    //Dashboard - 6
    public Long countMascotasActivas();
    
}
