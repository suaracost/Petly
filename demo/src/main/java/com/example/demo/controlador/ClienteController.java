package com.example.demo.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Mascota;
import com.example.demo.entidad.UserEntity;
import com.example.demo.repositorio.UserRepository;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.MascotaService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    MascotaService mascotaService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    //http://localhost:8090/cliente/all
    @GetMapping("/all")
    @Operation(summary = "Mostrar todos los clientes")
    public List<Cliente> showAllClients(Model model) {

        return clienteService.SearchAll();

    }

    //http://localhost:8090/cliente/details
    @GetMapping("/details")
    public ResponseEntity<Cliente> buscarCliente() {
        
        Cliente cliente = clienteService.SearchByCedula(
            SecurityContextHolder.getContext().getAuthentication().getName()
        );

        if (cliente == null) {
            return new ResponseEntity<Cliente>(cliente, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

    }

    //http://localhost:8090/cliente/find/1
    @GetMapping("/find/{id}")
    @Operation(summary = "Encontrar un cliente dado su id")
    public Cliente showClient(@PathVariable("id") Long identificacion) {
        Cliente cliente = clienteService.SearchById(identificacion);

        return cliente;
    }

    //http://localhost:8090/cliente/buscar/123
    @GetMapping("/buscar/{cedula}")
    @Operation(summary = "Encontrar un cliente dado su cedula")
    public Cliente showClientByCedula(@PathVariable("cedula") String cedula) {
        Cliente cliente = clienteService.SearchByCedula(cedula);

        return cliente;
    }

    //http://localhost:8090/cliente/agregar
    @PostMapping("/agregar")
    @Operation(summary = "Agregar un cliente")
    public ResponseEntity agregarCliente(@RequestBody Cliente cliente) {
        
        //clienteService.add(cliente);
        
        if(userRepository.existsByUsername(cliente.getCedula())){
            return new ResponseEntity<String>("Este usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = customUserDetailService.ClienteToUser(cliente);
        cliente.setUser(userEntity);
        Cliente newCliente = clienteService.add(cliente);

        if (newCliente == null) {
            return new ResponseEntity<Cliente>(newCliente, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Cliente>(newCliente, HttpStatus.CREATED);        

    }

    //http://localhost:8090/cliente/delete/1
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar un cliente dado su id")
    public void deleteClient(@PathVariable("id") Long identificacion) {
        clienteService.deleteById(identificacion);
    }

    //http://localhost:8090/cliente/update
    @PutMapping("/update")
    @Operation(summary = "Actualizar un cliente")
    public void updateClient(@RequestBody Cliente cliente) {
        Cliente aux = clienteService.SearchById(cliente.getId());
        aux.setCedula(cliente.getCedula());
        aux.setNombre(cliente.getNombre());
        aux.setApellido(cliente.getApellido());
        aux.setCelular(cliente.getCelular());
        aux.setCorreo(cliente.getCorreo());
        clienteService.update(aux);
    }

    //http://localhost:8090/usuario/1/mascota/1
    //Esta funcion trae los datos de la mascota asociada al usuario.
    @GetMapping("/{usuario}/mascota/{id}")
    @Operation(summary = "Encontrar una mascota con informacion de su dueño dado el id de la mascota")
    public Cliente showPet(@PathVariable("usuario") Long usuario, @PathVariable("id") Long identificacion) {
        
        Cliente cliente = clienteService.SearchById(usuario);

        Mascota mascota = mascotaService.SearchById(identificacion);

        cliente.setMascotas(new ArrayList<>() );

        if(mascota.getCliente().getCedula() == cliente.getCedula()){
            cliente.getMascotas().add(mascota);

        };
        
        return cliente;

    }
    
    
}
