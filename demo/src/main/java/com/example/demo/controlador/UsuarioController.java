package com.example.demo.controlador;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    
    @Autowired
    ClienteService clienteService;

    @Autowired
    MascotaService mascotaService;

    //http://localhost:8090/usuario/1
    //Esta funcion trae los datos del usuario con la lista de mascotas asociada.
    @GetMapping("/{id}")
    public Cliente mostrarPerfil(@PathVariable("id") Long identificacion) {
        Cliente cliente = clienteService.SearchById(identificacion);

        return cliente;
    }

    //http://localhost:8090/usuario/1/mascota/1
    //Esta funcion trae los datos de la mascota asociada al usuario.
    //TODO: Preguntar al profe, como se hace para traer la mascota con tal id. YA
    @GetMapping("/{usuario}/mascota/{id}")
    public Cliente showPet(Model model, @PathVariable("usuario") Long usuario, @PathVariable("id") Long identificacion) {
        
        Cliente cliente = clienteService.SearchById(usuario);

        Mascota mascota = mascotaService.SearchById(identificacion);

        cliente.setMascotas(new ArrayList<>() );

        if(mascota.getCliente().getCedula() == cliente.getCedula()){
            cliente.getMascotas().add(mascota);

        };
        

        return cliente;

    }
}
