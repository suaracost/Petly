package com.example.demo.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.assertj.core.api.Assertions; 
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import org.springframework.boot.test.context.SpringBootTest;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.Droga;
import com.example.demo.entidad.Mascota;
import com.example.demo.entidad.Tratamiento;
import com.example.demo.entidad.Veterinario;

import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.DrogaRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.VeterinarioRepository;

import com.example.demo.servicio.TratamientoService;

// Ejecuta una instancia completa de la aplicacion → esto hace que las pruebas se demoren mas 
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class TratamientoServiceTestNaive {
    
    @Autowired
    private TratamientoService tratamientoService;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private DrogaRepository drogaRepository;

    @BeforeEach
    void init() {


        
        //Leer las drogas del archivo excel y guardarlos en la base de datos
        try {
            InputStream file = getClass().getClassLoader().getResourceAsStream("MEDICAMENTOS_VETERINARIA_TEST.xlsx"); 
            
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet("MEDICAMENTOS BD FINAL");
            for(int rowIndex =1; rowIndex <= sheet.getLastRowNum(); rowIndex++){
                Row row = sheet.getRow(rowIndex);
                if(row != null){
                    Droga drug = new Droga();
                    drug.setNombre(row.getCell(0).getStringCellValue());
                    drug.setPrecioVenta((float) row.getCell(1).getNumericCellValue());
                    drug.setPrecioCompra((float) row.getCell(2).getNumericCellValue());
                    drug.setUnidadesDisponibles((int) row.getCell(3).getNumericCellValue());
                    drug.setUnidadesVendidas((int) row.getCell(4).getNumericCellValue());
                    drogaRepository.save(drug);
                }
            }

            if(workbook != null){
                workbook.close();
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }   
        
    }

    @Test
    public void TratamientoService_createTratamiento_Tratamiento() {
        
        // arrange
        Mascota mascota = new Mascota("Carmelo", "Criollo", 13, 33, "Esquiso", "https://ih1.redbubble.net/image.1304494203.2178/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg", "Disponible");
        mascotaRepository.save(mascota);

        Cliente cliente = new Cliente("1000000001", "Pedro", "Duran", "pedro@gmail.com", "12345671");
        clienteRepository.save(cliente);

        mascota.setCliente(cliente);

        Veterinario veterinario = new Veterinario("1000000051", "Alexander", "García", "Petly123", "https://universidadeuropea.com/resources/media/images/medicina-veterinaria-1200x630.original.jpg", "General", 0, "Disponible");
        veterinarioRepository.save(veterinario);

        Droga droga = drogaRepository.findByNombre("ACOLAN");

        String fecha = 27 + "-" + 4 + "-2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate fechaLocalDate = LocalDate.parse(fecha, formatter);

        // act
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setmascota(mascota);
        tratamiento.setveterinario(veterinario);
        tratamiento.setdroga(droga);
        tratamiento.setFecha(fechaLocalDate);

        tratamientoService.add(tratamiento);

        Tratamiento newTratamiento = tratamientoService.SearchById(tratamiento.getId());

        // assert
        Assertions.assertThat(newTratamiento).isNotNull();
    }

    @Test
    public void TratamientoService_findAll_TratamientoList() {

        // arrange
        Mascota mascota = new Mascota("Firulais", "Criollo", 13, 33, "Esquiso", "https://ih1.redbubble.net/image.1304494203.2178/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg", "Disponible");
        mascotaRepository.save(mascota);

        Cliente cliente = new Cliente("1000000001", "Pedro", "Duran", "pedro@gmail.com", "12345671");
        clienteRepository.save(cliente);

        mascota.setCliente(cliente);

        Veterinario veterinario = new Veterinario("1000000051", "Alexander", "García", "Petly123", "https://universidadeuropea.com/resources/media/images/medicina-veterinaria-1200x630.original.jpg", "General", 0, "Disponible");
        veterinarioRepository.save(veterinario);

        Droga droga = drogaRepository.findByNombre("ACOLAN");

        String fecha = 27 + "-" + 4 + "-2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate fechaLocalDate = LocalDate.parse(fecha, formatter);

        // act
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setmascota(mascota);
        tratamiento.setveterinario(veterinario);
        tratamiento.setdroga(droga);
        tratamiento.setFecha(fechaLocalDate);

        tratamientoService.add(tratamiento);

        List<Tratamiento> tratamientos = tratamientoService.SearchAll();

        // assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(1);
        
    }

}
