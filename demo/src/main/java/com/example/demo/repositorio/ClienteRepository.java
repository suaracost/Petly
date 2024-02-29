package com.example.demo.repositorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;

@Repository
public class ClienteRepository {
    private Map<Integer, Cliente> data = new HashMap<>();

    //Creacion base de datos falsa de Clientes
    public ClienteRepository() {
        // Crear una instancia de Mascota y agregarla a la lista de mascotas del cliente
        List<Mascota> mascotasPedro = new ArrayList<>();
        mascotasPedro.add(new Mascota(1, "Rex", "Bulldog", 2, 15, "Gastritis", "https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg", "Disponible"));
        mascotasPedro.add(new Mascota(2, "Scooby", "Gran Danes", 4, 25, "Diarrea", "https://ichef.bbci.co.uk/ace/ws/800/cpsprodpb/15665/production/_107435678_perro1.jpg", "Disponible"));
        mascotasPedro.add(new Mascota(3, "Pluto", "Labrador", 5, 30, "Gripe", "https://img.freepik.com/fotos-premium/perrito-lindo-pequeno-posando-alegre-aislado-gris_155003-42660.jpg", "Disponible"));
        

        data.put(1, new Cliente(1, "1000000001", "Pedro", "Duran", "pedro@gmail.com", "12345671", mascotasPedro)); // Agregar mascotas a Pedro
        data.put(2, new Cliente(2, "1000000002", "Juan", "Sanchez", "juan@gmail.com", "12345672", null));
        data.put(3, new Cliente(3, "1000000003", "Maria", "Perez", "maria@gmail.com", "12345673", null));
        data.put(4, new Cliente(4, "1000000004", "Luis", "Ramirez", "luis@gmail.com", "12345674", null));
        data.put(5, new Cliente(5, "1000000005", "Ana", "Lopez", "ana@gmail.com", "12345675", null));
        data.put(6, new Cliente(6, "1000000006", "Jose", "Gonzalez", "jose@gmail.com", "12345676", null));
    }

    //Metodos para mostrar las Clientes (queries)
    //Mostrar Cliente en especifico
    public Cliente findById(int id){
        return data.get(id);
    }

    //Metodo para buscar por cedula
    public Cliente findByCedula(String cedula){
        for (Cliente Cliente : data.values()) {
            if(Cliente.getCedula().equals(cedula)){
                return Cliente;
            }
        }
        return null;
    }

    //Mostrar todos los Clientes (findAll)
    public Collection<Cliente> findAll(){
        return data.values();
    }

    //Eliminar Cliente
    public void deleteById(int id){
        data.remove(id);
    }

    //Actualizar Cliente
    public void update(Cliente Cliente){
        data.put(Cliente.getId(), Cliente);
    }

    //Agregar Cliente
    public void add(Cliente Cliente){
        int tam = data.size();
        int lastId = data.get(tam).getId();
        Cliente.setId(lastId+1);
        data.put(Cliente.getId(), Cliente);
    }
}
