package com.example.demo.entidad;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//! Clase con patron Builder
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Veterinario {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    private String cedula;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String foto;
    private String especialidad;
    private int numAtenciones;
    private String estado;

    //Relacion con la tabla tratamiento para representar los tratamientos que ha hecho el veterinario
    @JsonIgnore
    @OneToMany (mappedBy = "veterinario")
    private List<Tratamiento> tratamientos = new ArrayList<>();

    public Veterinario(Long id, String cedula, String nombre, String apellido, String contrasena, String foto, String especialidad, int numAtenciones, String estado) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.foto = foto;
        this.especialidad = especialidad;
        this.numAtenciones = numAtenciones;
        this.estado = estado;
    }

    public Veterinario(String cedula, String nombre, String apellido, String contrasena, String foto, String especialidad, int numAtenciones, String estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.foto = foto;
        this.especialidad = especialidad;
        this.numAtenciones = numAtenciones;
        this.estado = estado;
    }

}