package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidad.Droga;

public interface DrogaService {
    
    public Droga SearchById(Long id);

    public Droga SearchByNombre(String nombre);

    public List<Droga> SearchAll();

    public void deleteById(Long id);

    public void update(Droga droga);

    public void add(Droga droga);

    //Dashboard - 7
    public Float totalVentasDrogas();

    //Dashboard - 8
    public Float totalGananciasDrogas();
}
