package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.servicio.MascotaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/mascotas")
@Controller
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    //http://localhost:8090/mascotas/all
    @GetMapping("/all")
    public String showAllPets(Model model) {
        model.addAttribute("mascotas", mascotaService.SearchAll());
        return "mostrarTodasMascotas"; //Esto retornara al HTML que se debe mostrar
    }

    //http://localhost:8090/mascotas/find/1
    @GetMapping("/find/{id}")
    public String showPet(Model model, @PathVariable("id") int identificacion) {
        model.addAttribute("mascota", mascotaService.SearchById(identificacion));
        return "mostrarMascota"; //Esto retornara al HTML que se debe mostrar
    }
    
}
