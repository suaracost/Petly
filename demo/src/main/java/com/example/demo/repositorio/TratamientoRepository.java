package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long>{
    
    //TODO: Preguntar al profe si esto sirve
    List<Tratamiento> findByMascotaTId(Long id);
    
    List<Tratamiento> findByVeterinarioTId(Long id);
}