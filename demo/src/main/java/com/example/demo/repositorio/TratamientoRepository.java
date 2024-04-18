package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long>{
    
    List<Tratamiento> findBymascotaId(Long id);
    
    List<Tratamiento> findByveterinarioId(Long id);

    //Dashboard - 1: Cantidad de tratamientos realizados en la veterinaria en el ultimo mes
    @Query(value = "SELECT COUNT(*) FROM tratamiento WHERE MONTH(fecha) = MONTH(CURRENT_DATE()) AND YEAR(fecha) = YEAR(CURRENT_DATE())", nativeQuery = true)
    Long countTratamientosMes();

    //Dashboard - 2: Cantidad de tratamientos por tipo de medicamento administrado en el ultimo mes (tabla medicamento-cantidad)
    @Query(value = "SELECT droga_id, COUNT(*) FROM tratamiento WHERE MONTH(fecha) = MONTH(CURRENT_DATE()) AND YEAR(fecha) = YEAR(CURRENT_DATE()) GROUP BY droga_id", nativeQuery = true)
    List<Object[]> countTratamientosPorDroga();


}