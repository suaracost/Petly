package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("veterinario/mascotas")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    @Autowired
    ClienteService clienteService;

    //http://localhost:8090/veterinario/mascotas/all
    @GetMapping("/all")
    public List<Mascota> showAllPets(Model model) {

        return mascotaService.SearchAll();

    }

    //http://localhost:8090/veterinario/mascotas/find/1
    //TODO: Preguntar al profe, falta que traiga la info del dueño de la mascota
    @GetMapping("/find/{id}")
    public Mascota showPet(@PathVariable("id") Long identificacion) {
        Mascota mascota = mascotaService.SearchById(identificacion);

        return mascota;
    }

    //http://localhost:8090/veterinario/mascotas/agregar
    @PostMapping("/agregar")
    public void agregarMascota(@RequestBody Mascota mascota) {

        mascotaService.add(mascota);
    }

    //http://localhost:8090/veterinario/mascotas/delete/1
    @DeleteMapping("/delete/{id}")
    public void deletePet(@PathVariable("id") Long identificacion) {
        mascotaService.updateState(identificacion);
    }
    
    //http://localhost:8090/veterinario/mascotas/update/1
    @GetMapping("/update/{id}")
    public String mostrarFormularioUpdate(@PathVariable("id") Long identificacion, Model model) {
        model.addAttribute("mascota", mascotaService.SearchById(identificacion));
        return "actualizarMascota";
    }

    //http://localhost:8090/veterinario/mascotas/update/1
    @PutMapping("/update/{id}")
    public void updatePet(@RequestBody Mascota mascota, @PathVariable("id") Long identificacion){
        //Esta parte es para que se mantenga el cliente al que pertenece la mascota
        Mascota aux = mascotaService.SearchById(identificacion);
        mascota.setCliente(aux.getCliente());
        mascotaService.update(mascota);
    }
    
    
}
