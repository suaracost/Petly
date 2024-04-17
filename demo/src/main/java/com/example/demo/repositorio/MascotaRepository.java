package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long>{
    
    //Metodo para buscar las mascotas asociadas al cliente
    Mascota findByClienteId(long id);

    //Dashboard - 5: Cantidad de mascotas totales en la veterinaria
    @Query("SELECT COUNT(m) FROM Mascota m")
    Long countMascotas();

    //Dasboard - 6: Cantidad de mascotas activas en la veterinaria
    @Query("SELECT COUNT(m) FROM Mascota m WHERE m.estado = 'Disponible'")
    Long countMascotasActivas();
}
