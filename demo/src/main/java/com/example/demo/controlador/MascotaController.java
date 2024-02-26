package com.example.demo.controlador;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.MascotaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
        Mascota mascota = mascotaService.SearchById(identificacion);

        if(mascota!=null){
            model.addAttribute("mascota", mascotaService.SearchById(identificacion));
        } else{
            throw new NotFoundException(identificacion);
        }

        return "mostrarMascota"; //Esto retornara al HTML que se debe mostrar
    }

    //http://localhost:8090/mascotas/add
    @GetMapping("/add")
    public String mostrarFormularioCreaString(Model model) {

        Mascota mascota = new Mascota(0, "", "", 0, 0, "", "", "");

        model.addAttribute("mascota", mascota);

        return "registroMascota";
    }

    //Metodo que recibe el POST del formulario para agregar mascota de (/mascotas/add)
    @PostMapping("/agregar")
    public String agregarMascota(@ModelAttribute("mascota") Mascota mascota) {
        mascotaService.add(mascota);
        return "redirect:/mascotas/all";
    }

    @GetMapping("/delete/{id}")
    public String deletePet(@PathVariable("id") int identificacion) {
        mascotaService.deleteById(identificacion);
        return "redirect:/mascotas/all";
    }
    
    @GetMapping("/update/{id}")
    public String mostrarFormularioUpdate(@PathVariable("id") int identificacion, Model model) {
        model.addAttribute("mascota", mascotaService.SearchById(identificacion));
        return "actualizarMascota";
    }

    @PostMapping("/update/{id}")
    public String updatePet(@PathVariable("id") int identificacion, @ModelAttribute("mascota") Mascota mascota) {
        mascotaService.update(mascota);
        return "redirect:/mascotas/all";
    }
    
    
}
