package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidad.Droga;
import com.example.demo.servicio.DrogaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("drogas")
@CrossOrigin(origins = "http://localhost:4200")
public class DrogaController {
    
    @Autowired
    DrogaService drogaService;

    //http://localhost:8090/drogas/all
    @GetMapping("/all")
    @Operation(summary = "Mostrar todas las drogas")
    public List<Droga> showAllTratamientos() {
        return drogaService.SearchAll();
    }
}
