package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entidad.Cliente;
import com.example.demo.servicio.ClienteService;

@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    ClienteService clienteService;

    @RequestMapping("/cliente")
    public String loginClient() {
        return "loginCliente";
    }

    @PostMapping("/cliente")
    public String loginClient(@RequestParam("cedula") String cedula) {
        Cliente client = clienteService.SearchByCedula(cedula);

        if(client != null) {
            return "redirect:/clientes/find/" + client.getId(); // Redirigir a la página del cliente
        } else{
            throw new CedulaNotFoundException(cedula);
        }
    }

    @RequestMapping("/vet")
    public String loginVet() {
        return "loginVeterinario";
    }

}
