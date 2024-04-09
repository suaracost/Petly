package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidad.Cliente;
import com.example.demo.servicio.ClienteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("veterinario/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //http://localhost:8090/veterinario/clientes/all
    @GetMapping("/all")
    public List<Cliente> showAllClients(Model model) {

        return clienteService.SearchAll();

    }

    //http://localhost:8090/veterinario/clientes/find/1
    @GetMapping("/find/{id}")
    public Cliente showClient(Model model, @PathVariable("id") Long identificacion) {
        Cliente cliente = clienteService.SearchById(identificacion);

        return cliente;
    }

    //http://localhost:8090/veterinario/clientes/agregar
    @PostMapping("/agregar")
    public void agregarCliente(@RequestBody Cliente cliente) {

        clienteService.add(cliente);

    }

    //http://localhost:8090/veterinario/clientes/delete/1
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") Long identificacion) {
        clienteService.deleteById(identificacion);
    }

    //http://localhost:8090/veterinario/clientes/update/1
    @PutMapping("/update/{id}")
    public void updateClient(@RequestBody Cliente cliente) {
        clienteService.update(cliente);
    }
    
    
}
