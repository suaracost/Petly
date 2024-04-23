package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Veterinario;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.VeterinarioService;


@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    VeterinarioService veterinarioService;

    //http://localhost:8090/login/veterinario
    @PostMapping("/veterinario")
    public ResponseEntity<?> loginVeterinario(@RequestBody LoginDTO loginDTO){
        Veterinario vet = veterinarioService.SearchByCedula(loginDTO.getCedula());
        if(vet == null){
            return ResponseEntity.badRequest().body("Cedula ingresada no existe");
        }
        if(vet.getContrasena().equals(loginDTO.getContrasena())){
            return ResponseEntity.ok().body(vet.getId());
        }
        return ResponseEntity.badRequest().body("Contraseña incorrecta");
    }
    
    //http://localhost:8090/login/cliente
    @PostMapping("/cliente")
    public ResponseEntity<?> loginCliente(@RequestBody LoginDTO loginDTO){
        Cliente cliente = clienteService.SearchByCedula(loginDTO.getCedula());
        if(cliente == null){
            return ResponseEntity.badRequest().body("Cedula ingresada no existe");
        }
        return ResponseEntity.ok().body(cliente.getId());
    }

}
