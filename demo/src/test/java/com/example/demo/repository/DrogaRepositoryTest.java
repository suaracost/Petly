package com.example.demo.repository;

import java.util.List;

import org.assertj.core.api.Assertions; 
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
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
import com.example.demo.repositorio.VeterinarioRepository;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@DataJpaTest
@RunWith(SpringRunner.class)
public class DrogaRepositoryTest {
    
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

        Mascota mascota1 = new Mascota("Firulais", "Criollo", 13, 33, "Esquiso", "https://ih1.redbubble.net/image.1304494203.2178/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg", "Disponible");
        Mascota mascota2 = new Mascota("Caramelo", "Criollo", 14, 33, "Daltonico", "https://ih1.redbubble.net/image.1304494203.2178/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg", "Inactivo");
        Mascota mascota3 = new Mascota("Eugene", "Criollo", 15, 33, "Desnutrido", "https://ih1.redbubble.net/image.1304494203.2178/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg", "Disponible");
        Mascota mascota4 = new Mascota("Chacho", "Criollo", 16, 33, "Paranoico", "https://ih1.redbubble.net/image.1304494203.2178/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg", "Disponible");

        mascotaRepository.save(mascota1);
        mascotaRepository.save(mascota2);
        mascotaRepository.save(mascota3);
        mascotaRepository.save(mascota4);

       Cliente cliente1 = new Cliente("1000000001", "Pedro", "Duran", "pedro@gmail.com", "12345671");
       Cliente cliente2 = new Cliente("1000000002", "Juan", "Sanchez", "juan@gmail.com", "12345672");

        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);

        Veterinario veterinario1 = new Veterinario("1000000000", "Administrador", "Petly", "Admin", "", "Administrador", 0, "Admin");
        Veterinario veterinario2 = new Veterinario("1000000051", "Alexander", "García", "Petly123", "https://universidadeuropea.com/resources/media/images/medicina-veterinaria-1200x630.original.jpg", "General", 0, "Disponible");
        Veterinario veterinario3 = new Veterinario("1000000052", "Isabella", "Martínez", "Petly123", "https://bensvet.com.br/wp-content/uploads/2020/03/blog-5-passos-para-se-tornar-o-melhor-m%C3%A9dico-veterin%C3%A1rio.jpg", "Quirurgico", 0, "Disponible");

        veterinarioRepository.save(veterinario1);
        veterinarioRepository.save(veterinario2);
        veterinarioRepository.save(veterinario3);

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

        // ALmacenar en una variable tipo Droga la droga con el id 1
        Droga drogaP = drogaRepository.findByNombre("ACOLAN");

        // asociar la mascota con el cliente
        mascota1.setCliente(cliente1);
        mascota2.setCliente(cliente1);
        mascota3.setCliente(cliente2);
        mascota4.setCliente(cliente2);
        mascotaRepository.save(mascota1);
        mascotaRepository.save(mascota2);
        mascotaRepository.save(mascota3);
        mascotaRepository.save(mascota4);

        // crear un tratamiento que asocie una mascota con un veterinario y una droga
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setMascota(mascota1);
        tratamiento.setVeterinario(veterinario1);

        veterinario1.setNumAtenciones(veterinario1.getNumAtenciones() + 1);
        veterinarioRepository.save(veterinario1);

        tratamiento.setDroga(drogaP);

        String fecha = 27 + "-" + 4 + "-2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate fechaLocalDate = LocalDate.parse(fecha, formatter);
        tratamiento.setFecha(fechaLocalDate);

        // Actualizar base de datos con la nueva info de la droga
        drogaP.setUnidadesDisponibles(drogaP.getUnidadesDisponibles() - 1);
        drogaP.setUnidadesVendidas(drogaP.getUnidadesVendidas() + 1);
        drogaRepository.save(drogaP);      
        
    }

// 3/5 Pruebas para diferentes consultas creadas por nosotros

    // Test (3) consulta creada por nosotros
    @Test
    public void DrogaRepository_totalVentasDrogas_Float() {

        //act
        drogaRepository.totalVentasDrogas();

        //assert
        Assertions.assertThat(drogaRepository.totalVentasDrogas()).isNotNull();
        Assertions.assertThat(drogaRepository.totalVentasDrogas()).isGreaterThan(0);
        
    }

    // Test (4) consulta creada por nosotros
    @Test
    public void DrogaRepository_totalGananciasDrogas_Float() {
        
        //act
        drogaRepository.totalGananciasDrogas();

        //assert
        Assertions.assertThat(drogaRepository.totalGananciasDrogas()).isNotNull();
        Assertions.assertThat(drogaRepository.totalGananciasDrogas()).isGreaterThan(0);

    }

    // Test (5) consulta creada por nosotros
    @Test
    public void DrogaRepository_topTresDrogasVendidas_List() {

        //act
        List<Object[]> top3 = drogaRepository.topTresDrogasVendidas();

        //assert
        Assertions.assertThat(top3).isNotNull();
        Assertions.assertThat(top3.size()).isEqualTo(3);
        Assertions.assertThat(top3.size()).isGreaterThan(0);
        
    }

// Terminan las 3/5 pruebas para diferentes consultas creadas por nosotros
// las 2 pruebas que faltan se le realizan al repositorio de Mascota en MascotaRepositoryTest.java

}
