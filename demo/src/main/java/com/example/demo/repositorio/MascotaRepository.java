package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long>{
    
    //Metodo para buscar las mascotas asociadas al cliente
    Mascota findByClienteId(long id);
}
