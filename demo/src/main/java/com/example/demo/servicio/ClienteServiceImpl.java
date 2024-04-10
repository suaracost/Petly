package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Cliente;
import com.example.demo.repositorio.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
    
    @Autowired
    ClienteRepository repo;

    
    @Override
    public Cliente SearchByCedula(String cedula) {
        return repo.findByCedula(cedula);
    }
    

    @Override
    public Cliente SearchById(Long id) {
        Optional<Cliente> optionalCliente = repo.findById(id);
        return optionalCliente.orElse(null);
    }

    @Override
    public List<Cliente> SearchAll(){
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Cliente cliente) {
        repo.save(cliente);
    }

    @Override
    public void add(Cliente cliente) {
        repo.save(cliente);
    }

}
