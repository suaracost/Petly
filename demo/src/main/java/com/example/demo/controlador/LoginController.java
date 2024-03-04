package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entidad.Cliente;
import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.servicio.ClienteService;

@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository repo;

    @RequestMapping("/cliente")
    public String loginClient() {
        return "loginCliente";
    }

    
    @PostMapping("/cliente")
    public String loginClient(@RequestParam("cedula") String cedula) {
        
        //Preguntar al profe cual de los dos es mejor
        //1 Usar el repositorio directamente (Aqui el metodo findByCedula se genera automaticamente por Spring Data JPA)
        Cliente client = repo.findByCedula(cedula);

        //2 Usar el servicio
        //Cliente client = clienteService.SearchByCedula(cedula);

        if(client != null) {
            return "redirect:/clientes/perfil/" + client.getId(); // Redirigir a la página del cliente
        } else{
            throw new CedulaNotFoundException(cedula);
        }
    }
    

    @RequestMapping("/vet")
    public String loginVet() {
        return "loginVeterinario";
    }

}
