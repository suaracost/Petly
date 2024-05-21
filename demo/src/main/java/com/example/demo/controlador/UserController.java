package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidad.Role;
import com.example.demo.entidad.UserEntity;
import com.example.demo.servicio.RolService;
import com.example.demo.servicio.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    
    @GetMapping("/rol/{cedula}")
    public Long buscarRol(@PathVariable String cedula) {
        System.out.println("\n \n");
        System.out.println("Buscando rol del usuario con cédula " + cedula);
        System.out.println("\n \n");
        UserEntity user = userService.findByUsername(cedula);
        if (user != null) {
            System.out.println("\n \n");
            System.out.println("Usuario con cédula " + user.getUsername() + " encontrado");
            Role role = rolService.findRoleByUserId(user.getId());
            if (role != null) {
                System.out.println("\n \n");
                System.out.println("El rol del usuario con cédula " + cedula + " es " + role.getId());
                System.out.println("\n \n");
                return role.getId();
            }
        }
        return null; // O puedes retornar un error HTTP adecuado
    }
    
    
}
