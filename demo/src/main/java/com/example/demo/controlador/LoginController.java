package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
@Controller
public class LoginController {
    
    @RequestMapping("/cliente")
    public String mostrarFormularioLogin(){
        return "login";
    }
    
}
