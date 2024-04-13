package com.example.demo.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("mascota")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

    @Autowired
    MascotaService mascotaService;

    @Autowired
    ClienteService clienteService;

    //http://localhost:8090/veterinario/mascotas/all
    @GetMapping("/all")
    public List<Mascota> showAllPets() {

        return mascotaService.SearchAll();

    }

    //http://localhost:8090/veterinario/mascotas/find/1
    //? Preguntar al profe, falta que traiga la info del dueño de la mascota
    @GetMapping("/find/{id}")
    public Cliente showPet(@PathVariable("id") Long identificacion) {
        Mascota mascota = mascotaService.SearchById(identificacion);

        if(mascota != null) {
            Cliente cliente = mascota.getCliente();

            cliente.setMascotas(new ArrayList<>());
            
            cliente.getMascotas().add(mascota);
        
            return cliente;
        } else {
            return null;
        }
    }

    @GetMapping("/buscar/{id}")
    public Mascota buscarMascota(@PathVariable("id") Long identificacion) {
        return mascotaService.SearchById(identificacion);
    }

    //http://localhost:8090/veterinario/mascotas/agregar/12345
    @PostMapping("/agregar/{cedula}")
    public void agregarMascota(@RequestBody Mascota mascota, @PathVariable("cedula") String cedula) {
        Cliente cliente = clienteService.SearchByCedula(cedula);
        mascota.setCliente(cliente);

        mascotaService.add(mascota);
    }

    //http://localhost:8090/veterinario/mascotas/delete/1
    @DeleteMapping("/delete/{id}")
    public void deletePet(@PathVariable("id") Long identificacion) {
        mascotaService.updateState(identificacion);
    }

    //http://localhost:8090/veterinario/mascotas/update
    @PutMapping("/update")
    public void updatePet(@RequestBody Mascota mascota){
        //Esta parte es para que se mantenga el cliente al que pertenece la mascota
        Mascota aux = mascotaService.SearchById(mascota.getId());
        mascota.setCliente(aux.getCliente());
        mascotaService.update(mascota);
    }
    
    
}
