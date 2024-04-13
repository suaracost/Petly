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

@RestController
@RequestMapping("veterinario")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {

    @Autowired
    VeterinarioService veterinarioService;

    //http://localhost:8090/veterinario/all
    @GetMapping("/all")
    public List<Veterinario> showAllVets() {
        return veterinarioService.SearchAll();
    }

    //http://localhost:8090/veterinario/find/1
    @GetMapping("/find/{id}")
    public Veterinario showVet(@PathVariable Long id) {
        return veterinarioService.SearchById(id);
    }

    //http://localhost:8090/veterinario/add
    @PostMapping("/add")
    public void addVet(@RequestBody Veterinario veterinario){
        veterinarioService.add(veterinario);
    }

    //http://localhost:8090/veterinario/delete/1
    @DeleteMapping("/delete/{id}")
    public void deleteVet(@PathVariable Long id){
        veterinarioService.deleteById(id);
    }

    //http://localhost:8090/veterinario/update/1
    @PutMapping("/update")
    public void updateVet(@RequestBody Veterinario veterinario){
        veterinarioService.update(veterinario);
    }
    
}
