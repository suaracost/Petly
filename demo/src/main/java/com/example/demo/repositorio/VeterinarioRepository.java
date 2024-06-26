package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{

    Veterinario findByCedula(String cedula);

    List<Veterinario> findByEspecialidadNot(String especialidad);

    //Dashboard - 3: Cantidad de veterinarios activos en la plataforma
    @Query("SELECT COUNT(v) FROM Veterinario v WHERE v.estado = 'Disponible'")
    Long countVeterinarios();

    //Dashboard - 4: Cantidad de veterinarios inactivos en la plataforma
    @Query("SELECT COUNT(v) FROM Veterinario v WHERE v.estado = 'Inactivo'")
    Long countVeterinariosInactivos();

}