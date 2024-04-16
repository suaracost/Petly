package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidad.Veterinario;
import com.example.demo.servicio.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("veterinario")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {

    @Autowired
    VeterinarioService veterinarioService;

    //http://localhost:8090/veterinario/all
    @GetMapping("/all")
    @Operation(summary = "Mostrar todos los veterinarios")
    public List<Veterinario> showAllVets() {
        return veterinarioService.SearchAll();
    }

    //http://localhost:8090/veterinario/find/1
    @GetMapping("/find/{id}")
    @Operation(summary = "Encontrar un veterinario dado su id")
    public Veterinario showVet(@PathVariable Long id) {
        return veterinarioService.SearchById(id);
    }

    //http://localhost:8090/veterinario/add
    @PostMapping("/add")
    @Operation(summary = "Agregar un veterinario")
    public void addVet(@RequestBody Veterinario veterinario){
        veterinarioService.add(veterinario);
    }

    //http://localhost:8090/veterinario/delete/1
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar un veterinario dado su id")
    public void deleteVet(@PathVariable Long id){
        veterinarioService.updateState(id);
    }

    //http://localhost:8090/veterinario/update
    @PutMapping("/update")
    @Operation(summary = "Actualizar un veterinario")
    public void updateVet(@RequestBody Veterinario veterinario){
        veterinarioService.update(veterinario);
    }
    
}
