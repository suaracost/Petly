package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;

@RequestMapping("usuario")
@Controller
public class UsuarioController {
    
    @Autowired
    ClienteService clienteService;

    @Autowired
    MascotaService mascotaService;

    //http://localhost:8090/usuario/1
    @GetMapping("/{id}")
    public String mostrarPerfil(Model model, @PathVariable("id") Long identificacion) {
        Cliente cliente = clienteService.SearchById(identificacion);

        if(cliente!=null){
            model.addAttribute("cliente", clienteService.SearchById(identificacion));
        } else{
            throw new NotFoundException(identificacion);
        }

        return "perfilCliente"; //Esto retornara al HTML que se debe mostrar
    }

    //http://localhost:8090/usuario/1/mascota/1
    @GetMapping("/{usuario}/mascota/{id}")
    public String showPet(Model model, @PathVariable("usuario") Long usuario, @PathVariable("id") Long identificacion) {
        Mascota mascota = mascotaService.SearchById(identificacion);

        if(mascota!=null){
            model.addAttribute("mascota", mascotaService.SearchById(identificacion));
            model.addAttribute("cliente", clienteService.SearchById(usuario));
        } else{
            throw new NotFoundException(identificacion);
        }

        return "mostrarMascotaCliente"; //Esto retornara al HTML que se debe mostrar
    }
}
