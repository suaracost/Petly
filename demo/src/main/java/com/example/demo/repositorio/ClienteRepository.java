package com.example.demo.repositorio;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Cliente;

@Repository
public class ClienteRepository {
    private Map<Integer, Cliente> data = new HashMap<>();

    //Creacion base de datos falsa de Clientes
    public ClienteRepository(){
        data.put(1, new Cliente(1, "1000000001", "Pedro", "Duran", "pedro@gmail.com", "12345671"));
        data.put(2, new Cliente(2, "1000000002", "Juan", "Sanchez", "juan@gmail.com", "12345672"));
        data.put(3, new Cliente(3, "1000000003", "Maria", "Perez", "maria@gmail.com", "12345673"));
        data.put(4, new Cliente(4, "1000000004", "Luis", "Ramirez", "luis@gmail.com", "12345674"));
        data.put(5, new Cliente(5, "1000000005", "Ana", "Lopez", "ana@gmail.com", "12345675"));
        data.put(6, new Cliente(6, "1000000006", "Jose", "Gonzalez", "jose@gmail.com", "12345676"));
    }

    //Metodos para mostrar las Clientes (queries)
    //Mostrar Cliente en especifico
    public Cliente findById(int id){
        return data.get(id);
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
