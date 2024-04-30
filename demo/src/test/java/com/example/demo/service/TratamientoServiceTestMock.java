package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Droga;
import com.example.demo.entidad.Mascota;
import com.example.demo.entidad.Tratamiento;
import com.example.demo.entidad.Veterinario;

import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;
import com.example.demo.servicio.TratamientoServiceImpl;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TratamientoServiceTestMock {

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private VeterinarioRepository veterinarioRepository;

    @Mock
    private DrogaRepository drogaRepository;
    
    @InjectMocks
    private TratamientoServiceImpl tratamientoService;    
    
    @Mock
    private TratamientoRepository tratamientoRepository;

    

    @BeforeEach
    void init() {
        
    }

    @Test
    public void TratamientoService_createTratamiento_Tratamiento() {
        
        //arrange        
        Mascota mascota1 = new Mascota("Firulais", "Criollo", 13, 33, "Esquiso", "https://ih1.redbubble.net/image.1304494203.2178/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg", "Disponible");
        mascotaRepository.save(mascota1);
        
        Cliente cliente1 = new Cliente("1000000001", "Pedro", "Duran", "pedro@gmail.com", "12345671");
        clienteRepository.save(cliente1);
        
        mascota1.setCliente(cliente1);
        mascotaRepository.save(mascota1);
        
        Veterinario veterinario2 = new Veterinario("1000000051", "Alexander", "García", "Petly123", "https://universidadeuropea.com/resources/media/images/medicina-veterinaria-1200x630.original.jpg", "General", 0, "Disponible");
        veterinarioRepository.save(veterinario2);

        Droga droga = new Droga();
        droga.setNombre("ACOLAN");
        droga.setPrecioVenta(151300.0f);
        droga.setPrecioCompra(60520.0f);
        droga.setUnidadesDisponibles(4);
        droga.setUnidadesVendidas(0);
        drogaRepository.save(droga);
        
        String fecha = 29 + "-" + 4 + "-2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate fechaLocalDate = LocalDate.parse(fecha, formatter);
        
        veterinario2.setNumAtenciones(veterinario2.getNumAtenciones() + 1);
        veterinarioRepository.save(veterinario2);
        
        
        
        // Guardar el nuevo tratamiento en la base de datos
        Tratamiento tratamiento = new Tratamiento(fechaLocalDate, mascota1, veterinario2, droga);
        when(tratamientoRepository.save(tratamiento)).thenReturn(
            tratamiento
        );

        tratamientoService.add(tratamiento);
        when(tratamientoRepository.findById(tratamiento.getId())).thenReturn( //
            java.util.Optional.of(tratamiento)
        );
        

        //act

        Optional<Tratamiento> tratamientos = tratamientoRepository.findById(tratamiento.getId());

        //assert
        Assertions.assertThat(tratamientos).isNotNull();
    }

    @Test
    public void TratamientoService_findAll_TratamientoList() {
        //arrange        
        Mascota mascota1 = new Mascota("Firulais", "Criollo", 13, 33, "Esquiso", "https://ih1.redbubble.net/image.1304494203.2178/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg", "Disponible");
        mascotaRepository.save(mascota1);
        
        Cliente cliente1 = new Cliente("1000000001", "Pedro", "Duran", "pedro@gmail.com", "12345671");
        clienteRepository.save(cliente1);
        
        mascota1.setCliente(cliente1);
        mascotaRepository.save(mascota1);
        
        Veterinario veterinario2 = new Veterinario("1000000051", "Alexander", "García", "Petly123", "https://universidadeuropea.com/resources/media/images/medicina-veterinaria-1200x630.original.jpg", "General", 0, "Disponible");
        veterinarioRepository.save(veterinario2);
        
        // Droga para el tratamiento1
        Droga droga1 = new Droga();
        droga1.setNombre("ACOLAN");
        droga1.setPrecioVenta(151300.0f);
        droga1.setPrecioCompra(60520.0f);
        droga1.setUnidadesDisponibles(4);
        droga1.setUnidadesVendidas(0);
        drogaRepository.save(droga1);
        // Droga para el tratamiento2
        Droga droga2 = new Droga();
        droga2.setNombre("Z-19");
        droga2.setPrecioVenta(150600.0f);
        droga2.setPrecioCompra(60240.0f);
        droga2.setUnidadesDisponibles(10);
        droga2.setUnidadesVendidas(0);
        drogaRepository.save(droga2);
        
        
        String fecha = 29 + "-" + 4 + "-2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate fechaLocalDate = LocalDate.parse(fecha, formatter);
        
        veterinario2.setNumAtenciones(veterinario2.getNumAtenciones() + 2);
        veterinarioRepository.save(veterinario2);
                
        
        // Guardar el nuevo tratamiento en la base de datos
        Tratamiento tratamiento = new Tratamiento(fechaLocalDate, mascota1, veterinario2, droga1);
        when(tratamientoRepository.save(tratamiento)).thenReturn(
            tratamiento
        );

        Tratamiento tratamiento2 = new Tratamiento(fechaLocalDate, mascota1, veterinario2, droga2);


        tratamientoService.add(tratamiento);
        when(tratamientoRepository.findAll()).thenReturn(
            List.of(tratamiento,tratamiento2)
        );
        

        //act
        List<Tratamiento> tratamientos = tratamientoRepository.findAll();

        //assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos).size().isEqualTo(2);
    }

    @Test
    public void TratamientoService_findByveterinarioId_TratamientoList() {
        //arrange        
        Mascota mascota1 = new Mascota("Firulais", "Criollo", 13, 33, "Esquiso", "https://ih1.redbubble.net/image.1304494203.2178/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg", "Disponible");
        mascotaRepository.save(mascota1);
        
        Cliente cliente1 = new Cliente("1000000001", "Pedro", "Duran", "pedro@gmail.com", "12345671");
        clienteRepository.save(cliente1);
        
        mascota1.setCliente(cliente1);
        mascotaRepository.save(mascota1);
        
        Veterinario veterinario2 = new Veterinario("1000000051", "Alexander", "García", "Petly123", "https://universidadeuropea.com/resources/media/images/medicina-veterinaria-1200x630.original.jpg", "General", 0, "Disponible");
        veterinarioRepository.save(veterinario2);
        
        // Droga para el tratamiento1
        Droga droga1 = new Droga();
        droga1.setNombre("ACOLAN");
        droga1.setPrecioVenta(151300.0f);
        droga1.setPrecioCompra(60520.0f);
        droga1.setUnidadesDisponibles(4);
        droga1.setUnidadesVendidas(0);
        drogaRepository.save(droga1);
        // Droga para el tratamiento2
        Droga droga2 = new Droga();
        droga2.setNombre("Z-19");
        droga2.setPrecioVenta(150600.0f);
        droga2.setPrecioCompra(60240.0f);
        droga2.setUnidadesDisponibles(10);
        droga2.setUnidadesVendidas(0);
        drogaRepository.save(droga2);
        // Droga para el tratamiento3
        Droga droga3 = new Droga();
        droga3.setNombre("BACIPREMIX");
        droga3.setPrecioVenta(188700.0f);
        droga3.setPrecioCompra(75480.0f);
        droga3.setUnidadesDisponibles(10);
        droga3.setUnidadesVendidas(0);
        drogaRepository.save(droga3);
        
        
        String fecha = 29 + "-" + 4 + "-2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate fechaLocalDate = LocalDate.parse(fecha, formatter);
        
        veterinario2.setNumAtenciones(veterinario2.getNumAtenciones() + 3);
        veterinarioRepository.save(veterinario2);
                
        
        // Guardar el nuevo tratamiento en la base de datos
        Tratamiento tratamiento = new Tratamiento(fechaLocalDate, mascota1, veterinario2, droga1);
        Tratamiento tratamiento2 = new Tratamiento(fechaLocalDate, mascota1, veterinario2, droga2);
        Tratamiento tratamiento3 = new Tratamiento(fechaLocalDate, mascota1, veterinario2, droga3);
        tratamientoRepository.save(tratamiento);
        tratamientoRepository.save(tratamiento2);
        tratamientoRepository.save(tratamiento3);

        tratamientoService.findByveterinarioId(veterinario2.getId());
        when(tratamientoRepository.findByveterinarioId(veterinario2.getId())).thenReturn(
            List.of(tratamiento,tratamiento2,tratamiento3)
        );

        //act
        List<Tratamiento> tratamientos = tratamientoRepository.findByveterinarioId(veterinario2.getId());

        //assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos).size().isEqualTo(3);

    }
    
}
