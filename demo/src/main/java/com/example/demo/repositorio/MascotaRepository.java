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
        data.put(1, new Mascota(1, "Rex", "Bulldog", 2, 15, "Gastritis", "https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg", "Disponible"));
        data.put(2, new Mascota(2, "Scooby", "Gran Danes", 4, 25, "Diarrea", "https://ichef.bbci.co.uk/ace/ws/800/cpsprodpb/15665/production/_107435678_perro1.jpg", "Disponible"));
        data.put(3, new Mascota(3, "Pluto", "Labrador", 5, 30, "Gripe", "https://img.freepik.com/fotos-premium/perrito-lindo-pequeno-posando-alegre-aislado-gris_155003-42660.jpg", "Adoptado"));
        data.put(4, new Mascota(4, "Max", "Beagle", 6, 18, "Healthy", "https://media.istockphoto.com/id/636475496/es/foto/retrato-de-cachorro-marr%C3%B3n-con-fondo-bokeh.jpg?s=612x612&w=0&k=20&c=xVLp2lHN7AhVyTMBXWTf1yfRyJovXT1R0hUwk98Riw8=", "Disponible"));
        data.put(5, new Mascota(5, "Charlie", "Poodle", 8, 12, "Healthy", "https://cdn.pixabay.com/photo/2019/02/06/15/18/puppy-3979350_640.jpg", "Disponible"));
        data.put(6, new Mascota(6, "Rocky", "Boxer", 9, 28, "Healthy", "https://www.ngenespanol.com/wp-content/uploads/2023/12/descubren-que-los-humanos-influimos-en-el-color-de-ojos-de-los-perros-770x431.jpg", "Adoptado"));
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

    //Eliminar mascota
    public void deleteById(int id){
        data.remove(id);
    }

    //Actualizar mascota
    public void update(Mascota mascota){
        data.put(mascota.getId(), mascota);
    }

    //Agregar mascota
    public void add(Mascota mascota){
        int tam = data.size();
        int lastId = data.get(tam).getId();
        mascota.setId(lastId+1);
        data.put(mascota.getId(), mascota);
    }
}
