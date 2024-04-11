package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidad.Cliente;
import com.example.demo.servicio.ClienteService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public Cliente showClient(@PathVariable("id") Long identificacion) {
        Cliente cliente = clienteService.SearchById(identificacion);

        return cliente;
    }

    //http://localhost:8090/veterinario/clientes/buscar/123
    @GetMapping("/buscar/{cedula}")
    public Cliente showClientByCedula(@PathVariable("cedula") String cedula) {
        Cliente cliente = clienteService.SearchByCedula(cedula);

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
        Cliente aux = clienteService.SearchById(cliente.getId());
        aux.setCedula(cliente.getCedula());
        aux.setNombre(cliente.getNombre());
        aux.setApellido(cliente.getApellido());
        aux.setCelular(cliente.getCelular());
        aux.setCorreo(cliente.getCorreo());
        clienteService.update(aux);
    }
    
    
}
