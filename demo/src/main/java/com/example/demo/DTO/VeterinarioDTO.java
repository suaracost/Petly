package com.example.demo.DTO;

import lombok.Data;

@Data
public class VeterinarioDTO {
    private String cedula;
    private String nombre;
    private String apellido;
    private String foto;
    private String especialidad;
    private int numAtenciones;
    private String estado;
    private Long id;
}
