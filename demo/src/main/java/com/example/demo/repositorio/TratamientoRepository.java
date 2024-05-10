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
    @Query(value = "SELECT d.nombre, COUNT(*) FROM tratamiento t INNER JOIN droga d ON t.droga_id = d.id WHERE MONTH(t.fecha) = MONTH(CURRENT_DATE()) AND YEAR(t.fecha) = YEAR(CURRENT_DATE()) GROUP BY d.nombre", nativeQuery = true)
    List<Object[]> countTratamientosPorDroga();

    //Query para el reporte mensual
    @Query(value = "SELECT MONTH(t.fecha), d.nombre, COUNT(*) FROM tratamiento t INNER JOIN droga d ON t.droga_id = d.id WHERE YEAR(t.fecha) = YEAR(CURRENT_DATE()) GROUP BY MONTH(t.fecha), d.nombre ORDER BY MONTH(t.fecha) ASC", nativeQuery = true)
    List<Object[]> tratamientosMensualesAnio();

    //Query para ventas mensuales
    @Query(value = "Select mes, sum(unidades*precio_venta) from (SELECT MONTH(t.fecha) as mes, d.nombre, COUNT(*) as unidades, d.precio_venta FROM tratamiento t INNER JOIN droga d ON t.droga_id = d.id WHERE YEAR(t.fecha) = YEAR(CURRENT_DATE()) GROUP BY MONTH(t.fecha), d.nombre ORDER BY MONTH(t.fecha) ASC) group by mes", nativeQuery = true)
    List<Object[]> ventasMensualesAnio();
}