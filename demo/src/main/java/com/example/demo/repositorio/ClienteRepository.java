package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    Cliente findByCedula(String cedula);

    //Metodo para buscar al cliente asociado a la mascota
    Cliente findByMascotasId(long id);
}
