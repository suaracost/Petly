package com.example.demo.entidad;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Veterinario {

    private String cedula;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String foto;
    private String especialidad;
    private int numAtenciones;
    private String estado;
    @Id
    @GeneratedValue
    private Long id;

    //Relacion con la tabla tratamiento para representar los tratamientos que ha hecho el veterinario
    @OneToMany (mappedBy = "veterinarioT")
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

    public Veterinario() {
        
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getNumAtenciones() {
        return numAtenciones;
    }

    public void setNumAtenciones(int numAtenciones) {
        this.numAtenciones = numAtenciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }
}