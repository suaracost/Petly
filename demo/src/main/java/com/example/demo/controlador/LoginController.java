package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Veterinario;
import com.example.demo.security.JWTGenerator;
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

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    //http://localhost:8090/login/veterinario
    @PostMapping("/veterinario")
    //public ResponseEntity<?> loginVeterinario(@RequestBody LoginDTO loginDTO){
    public ResponseEntity loginVeterinario(@RequestBody Veterinario veterinario){
        /*
        Veterinario vet = veterinarioService.SearchByCedula(loginDTO.getCedula());
        if(vet == null){
            return ResponseEntity.badRequest().body("Cedula ingresada no existe");
        }
        if(vet.getContrasena().equals(loginDTO.getContrasena())){
            return ResponseEntity.ok().body(vet.getId());
        }
        return ResponseEntity.badRequest().body("Contraseña incorrecta");
        */

        System.out.println("\n \n");
        System.out.println("Entrando al login veterinario" + veterinario.getCedula() + " " + veterinario.getContrasena());
        System.out.println("\n \n");

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(veterinario.getCedula(), veterinario.getContrasena())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        System.out.println("\n \n");
        System.out.println("El token generado es: " + token);
        System.out.println("\n \n");

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
    
    //http://localhost:8090/login/cliente
    @PostMapping("/cliente")
    //public ResponseEntity<?> loginCliente(@RequestBody LoginDTO loginDTO){
    public ResponseEntity loginCliente(@RequestBody Cliente cliente){
        /*
        Cliente cliente = clienteService.SearchByCedula(loginDTO.getCedula());
        if(cliente == null){
            return ResponseEntity.badRequest().body("Cedula ingresada no existe");
        }
        return ResponseEntity.ok().body(cliente.getId());
        */


        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(cliente.getCedula(), "123")
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<String>(token, HttpStatus.OK);
        

    }

}
