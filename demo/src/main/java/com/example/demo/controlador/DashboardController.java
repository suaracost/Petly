package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.servicio.ClienteService;
import com.example.demo.servicio.DrogaService;
import com.example.demo.servicio.MascotaService;
import com.example.demo.servicio.TratamientoService;
import com.example.demo.servicio.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    @Autowired
    MascotaService mascotaService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    DrogaService drogaService;

    @Autowired
    VeterinarioService veterinarioService;

    @Autowired
    TratamientoService tratamientoService;

    //http://localhost:8090/dashboard/tratamientos/administrados
    @GetMapping("/tratamientos/administrados")
    @Operation(summary = "Mostrar el conteo de la cantidad de tratamientos administrados en el ultimo mes en la veterinaria.")
    public Long showTotalTratamientos() {
        return tratamientoService.countTratamientosMes();
    }

    //http://localhost:8090/dashboard/tratamientos/cantidad_tipo
    @GetMapping("/tratamientos/cantidad_tipo")
    @Operation(summary = "Cantidad de tratamientos por tipo de medicamento administrado en el ultimo mes(tabla medicamento-cantidad)")
    public List<Object[]> showTotalTratamientosPorDroga() {
        return tratamientoService.countTratamientosPorDroga();
    }

    //http://localhost:8090/dashboard/veterinarios/total
    @GetMapping("/veterinarios/total")
    @Operation(summary = "Mostrar el conteo de la cantidad total de veterinarios en la veterinaria")
    public Long showTotalVets() {
        return veterinarioService.countVeterinarios();
    }

    //http://localhost:8090/dashboard/veterinarios/inactivos
    @GetMapping("/veterinarios/inactivos")
    @Operation(summary = "Mostrar el conteo de la cantidad de veterinarios inactivos en la veterinaria")
    public Long showInactiveVets() {
        return veterinarioService.countVeterinariosInactivos();
    }

    //http://localhost:8090/dashboard/mascotas/activas
    @GetMapping("/mascotas/activas")
    @Operation(summary = "Mostrar el conteo de la cantidad de mascotas activas en la veterinaria")
    public Long showActivePets() {
        return mascotaService.countMascotasActivas();
    }

    //http://localhost:8090/dashboard/mascotas/totales
    @GetMapping("/mascotas/totales")
    @Operation(summary = "Mostrar el conteo de la cantidad total de mascotas en la veterinaria")
    public Long showInactivePets() {
        return mascotaService.countMascotas();
    }

    //http://localhost:8090/dashboard/droga/ganancias
    @GetMapping("/droga/ganancias")
    @Operation(summary = "Mostrar el total de ganancias de las drogas")
    public Float showTotalGananciasDrogas() {
        return drogaService.totalGananciasDrogas();
    }

    //http://localhost:8090/dashboard/droga/ventas
    @GetMapping("/droga/ventas")
    @Operation(summary = "Mostrar el total de ventas de las drogas")
    public Float showTotalVentasDrogas() {
        return drogaService.totalVentasDrogas();
    }    

    //http://localhost:8090/dashboard/droga/top
    @GetMapping("/droga/top")
    @Operation(summary = "Mostrar el top tres de drogas vendidas")
    public List<Object[]> showTopTresDrogasVendidas() {
        return drogaService.topTresDrogasVendidas();
    }
}
