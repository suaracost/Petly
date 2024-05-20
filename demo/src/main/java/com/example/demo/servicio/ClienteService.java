package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidad.Cliente;

public interface ClienteService {

    public Cliente SearchById(Long id);

    public Cliente SearchByCedula(String cedula);

    public List<Cliente> SearchAll();

    public void deleteById(Long id);

    public void update(Cliente cliente);

    public Cliente add(Cliente cliente);
}
