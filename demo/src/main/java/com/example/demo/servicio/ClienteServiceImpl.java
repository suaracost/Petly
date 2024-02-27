package com.example.demo.servicio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Cliente;
import com.example.demo.repositorio.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
    
    @Autowired
    ClienteRepository repo;

    @Override
    public Cliente SearchById(int id) {
        return repo.findById(id);
    }

    @Override
    public Collection<Cliente> SearchAll(){
        return repo.findAll();
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Cliente cliente) {
        repo.update(cliente);
    }

    @Override
    public void add(Cliente cliente) {
        repo.add(cliente);
    }

    
}
