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

@RestController
@RequestMapping("tratamiento")
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientoController {

    @Autowired
    TratamientoService tratamientoService;

    //http://localhost:8090/tratamiento/all
    @GetMapping("/all")
    public List<Tratamiento> showAllTratamientos() {
        return tratamientoService.SearchAll();
    }

    //http://localhost:8090/tratamiento/find/1
    @GetMapping("/find/{id}")
    public Tratamiento showTratamiento(@PathVariable Long id) {
        return tratamientoService.SearchById(id);
    }

    //http://localhost:8090/tratamiento/add
    //TODO: Preguntarle al profesor como asociarle el idDroga, idVeterinario y idMascota
    @PostMapping("/add")
    public void addTratamiento(@RequestBody Tratamiento tratamiento){
        tratamientoService.add(tratamiento);
    }
    
}
