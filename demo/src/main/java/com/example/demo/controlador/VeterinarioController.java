package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.VeterinarioDTO;
import com.example.demo.DTO.VeterinarioMapper;
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
    public ResponseEntity<List<VeterinarioDTO>> showAllVets() {

        List<Veterinario> veterinarios = veterinarioService.findByEspecialidadNotAdmin();
        List<VeterinarioDTO> veterinariosDTO = VeterinarioMapper.INSTANCE.convertList(veterinarios);

        //Se envia el codigo de respuesta y la lista de veterinarios encontrada
        ResponseEntity<List<VeterinarioDTO>> response = new ResponseEntity<>(veterinariosDTO, HttpStatus.OK);
        
        return response;
    }

    //http://localhost:8090/veterinario/find/1
    @GetMapping("/find/{id}")
    @Operation(summary = "Encontrar un veterinario dado su id")
    public ResponseEntity<VeterinarioDTO> showVet(@PathVariable Long id) {

        Veterinario vet = veterinarioService.SearchById(id);
        VeterinarioDTO vetDTO = VeterinarioMapper.INSTANCE.convert(vet);

        if (vet == null) {
            return new ResponseEntity<VeterinarioDTO>(vetDTO, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VeterinarioDTO>(vetDTO, HttpStatus.OK);
    }

    //http://localhost:8090/veterinario/add
    @PostMapping("/add")
    @Operation(summary = "Agregar un veterinario")
    public ResponseEntity<VeterinarioDTO> addVet(@RequestBody Veterinario veterinario){

        Veterinario vet = veterinarioService.add(veterinario);
        VeterinarioDTO vetDTO = VeterinarioMapper.INSTANCE.convert(vet);

        if (vet == null) {
            return new ResponseEntity<VeterinarioDTO>(vetDTO, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<VeterinarioDTO>(vetDTO, HttpStatus.CREATED);
    }

    //http://localhost:8090/veterinario/delete/1
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar un veterinario dado su id")
    public ResponseEntity<String> deleteVet(@PathVariable Long id){
        Veterinario vet = veterinarioService.SearchById(id);

        if (vet == null) {
            return new ResponseEntity<String>("VETERINARIO NOT FOUND", HttpStatus.NOT_FOUND);
        }

        veterinarioService.updateState(id);

        return new ResponseEntity<>("STATE UPDATED", HttpStatus.OK);
    }

    //http://localhost:8090/veterinario/update
    @PutMapping("/update")
    @Operation(summary = "Actualizar un veterinario")
    public ResponseEntity<VeterinarioDTO> updateVet(@RequestBody Veterinario veterinario){
        Veterinario vetUpdated = veterinarioService.update(veterinario);
        VeterinarioDTO vetDTO = VeterinarioMapper.INSTANCE.convert(vetUpdated);

        if (vetUpdated == null) {
            return new ResponseEntity<VeterinarioDTO>(vetDTO, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<VeterinarioDTO>(vetDTO, HttpStatus.OK);
    }
    
}
