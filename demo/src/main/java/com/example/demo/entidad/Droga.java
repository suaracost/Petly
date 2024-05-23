package com.example.demo.entidad;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Droga {

    private String nombre;
    private float precioCompra;
    private float precioVenta;
    private int unidadesDisponibles;
    private int unidadesVendidas;
    @Id
    @GeneratedValue
    private Long id;

    //Relacion con la tabla tratamiento para representar los tratamientos que tienen la droga
    @JsonIgnore
    @OneToMany (mappedBy = "droga")
    private List<Tratamiento> tratamientos = new ArrayList<>();

}