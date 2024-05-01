package com.example.demo.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.controlador.VeterinarioController;
import com.example.demo.entidad.Veterinario;
import com.example.demo.servicio.VeterinarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

//Prueba de integracion: Para probar un componente se deben probar los otros, entonces debemos usar el repo
// servicio entonces se debe simular el servicio

//Pruebas de integracion

@WebMvcTest(controllers = VeterinarioController.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class VeterinarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeterinarioService veterinarioService;

    //Poder pasar de un objeto a su representacion en JSON
    @Autowired
    ObjectMapper objectMapper;

    //Get all
    @Test
    public void VeterinarioController_showAllVets_ListVeterinario() throws Exception{
        when(veterinarioService.findByEspecialidadNotAdmin()).thenReturn(
            List.of(
                new Veterinario(
                    "123457890",
                    "Juan1",
                    "Perez",
                    "Petly123",
                    "Foto.jpg",
                    "Cirujano",
                    2,
                    "Disponible"
                ),
                new Veterinario(
                    "12345890",
                    "Juan2",
                    "Perez",
                    "Petly123",
                    "Foto.jpg",
                    "Cirujano",
                    2,
                    "Disponible"
                ),
                new Veterinario(
                    "14567890",
                    "Juan3",
                    "Perez",
                    "Petly123",
                    "Foto.jpg",
                    "Cirujano",
                    2,
                    "Disponible"
                )
            ));

        ResultActions response = mockMvc.perform(
            get("/veterinario/all")
        );

        response.andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.size()").value(3));
    }

    //Find (Get) by id inexistente
    @Test
    public void VeterinarioController_showVet_Veterinario() throws Exception{

        when(veterinarioService.SearchById(anyLong())).thenReturn(
            null
        );

        ResultActions response = mockMvc.perform(
            get("/veterinario/find/").param("id", "2")
        );

        response.andExpect(status().isNotFound());

    }

    //Find (Get) by id existente
    @Test
    public void VeterinarioController_showVet2_Veterinario() throws Exception{

        when(veterinarioService.SearchById(anyLong())).thenReturn(
            new Veterinario(
                "1234567890",
                "Juan",
                "Perez",
                "Petly123",
                "Foto.jpg",
                "Cirujano",
                2,
                "Disponible"
            )
        );

        ResultActions response = mockMvc.perform(
            get("/veterinario/find/2")
        );

        response.andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.nombre").value("Juan"))
        .andExpect(jsonPath("$.apellido").value("Perez"))
        .andExpect(jsonPath("$.cedula").value("1234567890"))
        .andExpect(jsonPath("$.especialidad").value("Cirujano"));
    }

    //Post (Add)
    @Test
    public void VeterinarioController_agregarVeterinario_Veterinario() throws Exception{
        Veterinario veterinario = new Veterinario(
            "1234567890",
            "Juan",
            "Perez",
            "Petly123",
            "Foto.jpg",
            "Cirujano",
            2,
            "Disponible"
        );
    
        when(veterinarioService.add(Mockito.any(Veterinario.class))).thenReturn(veterinario);
    
        ResultActions response = mockMvc.perform(
            post("/veterinario/add")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(veterinario)));
    
        response.andExpect(status().isCreated())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.nombre").value(veterinario.getNombre()))
        .andExpect(jsonPath("$.apellido").value(veterinario.getApellido()))
        .andExpect(jsonPath("$.cedula").value(veterinario.getCedula()))
        .andExpect(jsonPath("$.especialidad").value(veterinario.getEspecialidad()));
    }

    //Delete OK
    @Test
    public void VeterinarioController_deleteVet2_String() throws Exception{

        when(veterinarioService.SearchById(anyLong())).thenReturn(
            new Veterinario(
                "1234567890",
                "Juan",
                "Perez",
                "Petly123",
                "Foto.jpg",
                "Cirujano",
                2,
                "Activo"
            )
        );

        when(veterinarioService.updateState(anyLong())).thenReturn(
            new Veterinario(
                "1234567890",
                "Juan",
                "Perez",
                "Petly123",
                "Foto.jpg",
                "Cirujano",
                2,
                "Activo"
            )
        );

        ResultActions response = mockMvc.perform(
            delete("/veterinario/delete/1")
        );

        response.andExpect(status().isOk());
    }

    //Delete ERROR
    @Test
    public void VeterinarioController_deleteVet_String() throws Exception{

        when(veterinarioService.SearchById(anyLong())).thenReturn(
            null
        );

        ResultActions response = mockMvc.perform(
            delete("/veterinario/delete/1")
        );

        response.andExpect(status().isNotFound());
    }

    //Update OK
    @Test
    public void VeterinarioController_updateVet_Veterinario() throws Exception{

        Veterinario veterinario = new Veterinario(
            "1234567890",
            "Juan",
            "Perez",
            "Petly123",
            "Foto.jpg",
            "Cirujano",
            2,
            "Disponible"
        );

        when(veterinarioService.update(Mockito.any(Veterinario.class))).thenReturn(veterinario);

        ResultActions response = mockMvc.perform(
            put("/veterinario/update")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(veterinario)));

        response.andExpect(status().isOk())

        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.nombre").value(veterinario.getNombre()))
        .andExpect(jsonPath("$.apellido").value(veterinario.getApellido()))
        .andExpect(jsonPath("$.cedula").value(veterinario.getCedula()))
        .andExpect(jsonPath("$.especialidad").value(veterinario.getEspecialidad()));
    }

    //Update ERROR
    @Test
    public void VeterinarioController_updateVet2_Veterinario() throws Exception{

        Veterinario veterinario = new Veterinario(
            "1234567890",
            "Juan",
            "Perez",
            "Petly123",
            "Foto.jpg",
            "Cirujano",
            2,
            "Disponible"
        );

        when(veterinarioService.update(Mockito.any(Veterinario.class))).thenReturn(null);

        ResultActions response = mockMvc.perform(
            put("/veterinario/update")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(veterinario)));

        response.andExpect(status().isBadRequest());
    }
    

}
