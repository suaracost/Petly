package com.example.demo.controlador;

import java.time.LocalDate;
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
import com.example.demo.entidad.Veterinario;
import com.example.demo.servicio.VeterinarioService;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.MascotaService;
import com.example.demo.entidad.Droga;
import com.example.demo.servicio.DrogaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("tratamiento")
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientoController {

    @Autowired
    TratamientoService tratamientoService;

    @Autowired
    VeterinarioService veterinarioService;

    @Autowired
    MascotaService mascotaService;

    @Autowired
    DrogaService drogaService;

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
        return tratamientoService.findBymascotaId(id_mascota);
    }

    //http://localhost:8090/tratamiento/find/veterinario/1
    //Encontrar los tratamientos de un veterinario dada su id
    @GetMapping("/find/veterinario/{id_veterinario}")
    @Operation(summary = "Encontrar los tratamientos de un veterinario dado su id")
    public List<Tratamiento> showTratamientoVeterinario(@PathVariable Long id_veterinario) {
        return tratamientoService.findByveterinarioId(id_veterinario);
    }

    //http://localhost:8090/tratamiento/add
    @PostMapping("/add/{id_vet}/{id_pet}")
    @Operation(summary = "Agregar un tratamiento")
    public void addTratamiento(@PathVariable("id_vet") Long id_veterinario, @PathVariable("id_pet") Long id_pet, @RequestBody String nombre){
        Tratamiento tratamiento = new Tratamiento();

        LocalDate fecha = LocalDate.now();
        tratamiento.setFecha(fecha);

        Veterinario vet = veterinarioService.SearchById(id_veterinario);
        //Agregar una atencion al veterinario
        vet.setNumAtenciones(vet.getNumAtenciones()+1);
        veterinarioService.add(vet);
        tratamiento.setVeterinario(vet);

        Mascota pet = mascotaService.SearchById(id_pet);
        tratamiento.setMascota(pet);
        
        Droga drug = drogaService.SearchByNombre(nombre);
        //Actualizar unidades disponibles y vendidas
        drug.setUnidadesDisponibles(drug.getUnidadesDisponibles()-1);
        drug.setUnidadesVendidas(drug.getUnidadesVendidas()+1);
        drogaService.add(drug);
        tratamiento.setDroga(drug);

        tratamientoService.add(tratamiento);
    }
    
}
