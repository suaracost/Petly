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

    public List<Droga> findByUnidadesDisponiblesGreaterThan(Integer cantidad);

    //Dashboard - 7
    public Float totalVentasDrogas();

    //Dashboard - 8
    public Float totalGananciasDrogas();

    //Dashboard - 9
    public List<Object[]> topTresDrogasVendidas();

    public List<Object[]> gastosIngresos();
}
