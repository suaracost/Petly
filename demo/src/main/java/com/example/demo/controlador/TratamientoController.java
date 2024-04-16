package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidad.Tratamiento;
import com.example.demo.servicio.TratamientoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("tratamiento")
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientoController {

    @Autowired
    TratamientoService tratamientoService;

    //http://localhost:8090/tratamiento/all
    @GetMapping("/all")
    @Operation(summary = "Mostrar todos los tratamientos")
    public List<Tratamiento> showAllTratamientos() {
        return tratamientoService.SearchAll();
    }

    //http://localhost:8090/tratamiento/find/mascota/1
    //Encontrar los tratamientos de una mascota dada su id
    @GetMapping("/find/mascota/{id_mascota}")
    @Operation(summary = "Encontrar los tratamientos de una mascota dado su id")
    public List<Tratamiento> showTratamientoMascota(@PathVariable Long id_mascota) {
        return tratamientoService.findByMascotaTId(id_mascota);
    }

    //http://localhost:8090/tratamiento/find/veterinario/1
    //Encontrar los tratamientos de un veterinario dada su id
    @GetMapping("/find/veterinario/{id_veterinario}")
    @Operation(summary = "Encontrar los tratamientos de un veterinario dado su id")
    public List<Tratamiento> showTratamientoVeterinario(@PathVariable Long id_veterinario) {
        return tratamientoService.findByVeterinarioTId(id_veterinario);
    }

    //http://localhost:8090/tratamiento/add
    //TODO: Preguntarle al profesor como asociarle el idDroga, idVeterinario y idMascota
    @PostMapping("/add")
    @Operation(summary = "Agregar un tratamiento")
    public void addTratamiento(@RequestBody Tratamiento tratamiento){
        tratamientoService.add(tratamiento);
    }
    
}
