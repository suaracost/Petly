package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long>{
    
    List<Tratamiento> findBymascotaId(Long id);
    
    List<Tratamiento> findByveterinarioId(Long id);

    //Dashboard - 1: Cantidad de tratamientos realizados en la veterinaria en el ultimo mes
    // @Query("SELECT COUNT(t) FROM Tratamiento t WHERE SUBSTRING(t.fecha, 4, 2) = EXTRACT(MONTH FROM CURRENT_DATE()) AND SUBSTRING(t.fecha, 7) = EXTRACT(YEAR FROM CURRENT_DATE())")
    // Long countTratamientosMes();

}