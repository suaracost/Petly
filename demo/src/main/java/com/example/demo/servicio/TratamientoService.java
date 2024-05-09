package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidad.Tratamiento;

public interface TratamientoService {
    
    public Tratamiento SearchById(Long id);

    public List<Tratamiento> SearchAll();

    public void deleteById(Long id);

    public void update(Tratamiento tratamiento);

    public void add(Tratamiento tratamiento);

    public List<Tratamiento> findBymascotaId(Long id);

    public List<Tratamiento> findByveterinarioId(Long id);

    //Dashboard - 1
    public Long countTratamientosMes();

    //Dashboard - 2
    public List<Object[]> countTratamientosPorDroga();

    //Query para el reporte mensual
    public List<Object[]> tratamientosMensualesAnio();

    //Query para las ganancias anuales por mes
    public List<Object[]> gananciasMensualesAnio();
}
