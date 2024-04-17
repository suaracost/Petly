package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Droga;

@Repository
public interface DrogaRepository extends JpaRepository<Droga, Long>{

    Droga findByNombre(String nombre);

    //Dashboard - 7: Ventas totales de drogas en la veterinaria
    @Query("SELECT SUM(d.precioVenta * d.unidadesVendidas) FROM Droga d")
    Float totalVentasDrogas();

    //Dashboard - 8: Ganancias totales en la veterinaria
    @Query("SELECT SUM(d.precioVenta * d.unidadesVendidas - d.precioCompra * d.unidadesVendidas) FROM Droga d")
    Float totalGananciasDrogas();
}
