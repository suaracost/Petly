package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Droga;

@Repository
public interface DrogaRepository extends JpaRepository<Droga, Long>{

    Droga findByNombre(String nombre);

    List<Droga> findByUnidadesDisponiblesGreaterThan(Integer cantidad);

    //Dashboard - 7: Ventas totales de drogas en la veterinaria
    @Query("SELECT SUM(d.precioVenta * d.unidadesVendidas) FROM Droga d")
    Float totalVentasDrogas();

    //Dashboard - 8: Ganancias totales en la veterinaria
    @Query("SELECT SUM(d.precioVenta * d.unidadesVendidas - d.precioCompra * d.unidadesVendidas) FROM Droga d")
    Float totalGananciasDrogas();

    //Dashboard - 9: Top tres tratamientos con mas unidades vendidas
    @Query(value = "SELECT d.nombre, SUM(d.unidades_vendidas) FROM droga d GROUP BY d.nombre ORDER BY SUM(d.unidades_vendidas) DESC LIMIT 3", nativeQuery = true)
    List<Object[]> topTresDrogasVendidas();
}
