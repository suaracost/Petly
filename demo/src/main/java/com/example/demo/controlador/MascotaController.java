package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RequestMapping("/mascotas")
@Controller
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    @Autowired
    ClienteService clienteService;

    //http://localhost:8090/mascotas/all
    @GetMapping("/all")
    public String showAllPets(Model model) {
        model.addAttribute("mascotas", mascotaService.SearchAll());
        return "mostrarTodasMascotas"; //Esto retornara al HTML que se debe mostrar
    }

    //http://localhost:8090/mascotas/find/1
    @GetMapping("/find/{id}")
    public String showPet(Model model, @PathVariable("id") Long identificacion) {
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

        Mascota mascota = new Mascota("", "", 0, 0, "", "", "");

        model.addAttribute("mascota", mascota);

        return "registroMascota";
    }

    //Metodo que recibe el POST del formulario para agregar mascota de (/mascotas/add)
    @PostMapping("/agregar")
    public String agregarMascota(@ModelAttribute("mascota") Mascota mascota, @RequestParam("cedulaDueno") String cedula) {
        
        Cliente cliente = clienteService.SearchByCedula(cedula);

        if(cliente!=null){
            mascota.setCliente(cliente);
            mascota.setEstado("Disponible");
            mascotaService.add(mascota);
        } else {
            throw new CedulaNotFoundException(cedula);
        }

        return "redirect:/mascotas/all";
    }

    @GetMapping("/delete/{id}")
    public String deletePet(@PathVariable("id") Long identificacion) {
        mascotaService.deleteById(identificacion);
        return "redirect:/mascotas/all";
    }
    
    @GetMapping("/update/{id}")
    public String mostrarFormularioUpdate(@PathVariable("id") Long identificacion, Model model) {
        model.addAttribute("mascota", mascotaService.SearchById(identificacion));
        return "actualizarMascota";
    }

    @PostMapping("/update/{id}")
    public String updatePet(@PathVariable("id") Long identificacion, @ModelAttribute("mascota") Mascota mascota) {
        Mascota aux = mascotaService.SearchById(identificacion);
        mascota.setCliente(aux.getCliente());
        mascotaService.update(mascota);
        return "redirect:/mascotas/all";
    }
    
    
}
