package com.example.demo.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    MascotaService mascotaService;

    //http://localhost:8090/cliente/all
    @GetMapping("/all")
    public List<Cliente> showAllClients(Model model) {

        return clienteService.SearchAll();

    }

    //http://localhost:8090/cliente/find/1
    @GetMapping("/find/{id}")
    public Cliente showClient(@PathVariable("id") Long identificacion) {
        Cliente cliente = clienteService.SearchById(identificacion);

        return cliente;
    }

    //http://localhost:8090/cliente/buscar/123
    @GetMapping("/buscar/{cedula}")
    public Cliente showClientByCedula(@PathVariable("cedula") String cedula) {
        Cliente cliente = clienteService.SearchByCedula(cedula);

        return cliente;
    }

    //http://localhost:8090/cliente/agregar
    @PostMapping("/agregar")
    public void agregarCliente(@RequestBody Cliente cliente) {

        clienteService.add(cliente);

    }

    //http://localhost:8090/cliente/delete/1
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") Long identificacion) {
        clienteService.deleteById(identificacion);
    }

    //http://localhost:8090/cliente/update
    @PutMapping("/update")
    public void updateClient(@RequestBody Cliente cliente) {
        Cliente aux = clienteService.SearchById(cliente.getId());
        aux.setCedula(cliente.getCedula());
        aux.setNombre(cliente.getNombre());
        aux.setApellido(cliente.getApellido());
        aux.setCelular(cliente.getCelular());
        aux.setCorreo(cliente.getCorreo());
        clienteService.update(aux);
    }

    //http://localhost:8090/usuario/1/mascota/1
    //Esta funcion trae los datos de la mascota asociada al usuario.
    @GetMapping("/{usuario}/mascota/{id}")
    public Cliente showPet(@PathVariable("usuario") Long usuario, @PathVariable("id") Long identificacion) {
        
        Cliente cliente = clienteService.SearchById(usuario);

        Mascota mascota = mascotaService.SearchById(identificacion);

        cliente.setMascotas(new ArrayList<>() );

        if(mascota.getCliente().getCedula() == cliente.getCedula()){
            cliente.getMascotas().add(mascota);

        };
        
        return cliente;

    }
    
    
}
