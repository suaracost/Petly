package com.example.demo.repositorio;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Mascota;

@Repository
public class MascotaRepository {
    private Map<Integer, Mascota> data = new HashMap<>();

    //Creacion base de datos falsa de mascotas
    public MascotaRepository(){
        data.put(1, new Mascota("Firulais", "Pastor Aleman", 3, 20, "Moquillo", "foto1.jpg", "Adoptado"));
        data.put(2, new Mascota("Rex", "Bulldog", 2, 15, "Gastritis", "foto2.jpg", "Disponible"));
        data.put(3, new Mascota("Scooby", "Gran Danes", 4, 25, "Diarrea", "foto3.jpg", "Disponible"));
        data.put(4, new Mascota("Pluto", "Labrador", 5, 30, "Gripe", "foto4.jpg", "Adoptado"));
        data.put(5, new Mascota("Max", "Beagle", 6, 18, "Healthy", "foto5.jpg", "Disponible"));
        data.put(6, new Mascota("Buddy", "Golden Retriever", 7, 30, "Healthy", "foto6.jpg", "Adoptado"));
        data.put(7, new Mascota("Charlie", "Poodle", 8, 12, "Healthy", "foto7.jpg", "Disponible"));
        data.put(8, new Mascota("Rocky", "Boxer", 9, 28, "Healthy", "foto8.jpg", "Adoptado"));
    }

    //Metodos para mostrar las mascotas (queries)
    //Mostrar mascota en especifico
    public Mascota findById(int id){
        return data.get(id);
    }

    //Mostrar todas las mascotas (findAll)
    public Collection<Mascota> findAll(){
        return data.values();
    }
}