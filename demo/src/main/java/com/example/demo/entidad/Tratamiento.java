package com.example.demo.entidad;


import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tratamiento {

    private LocalDate fecha;
    @Id
    @GeneratedValue
    private Long id;

    //Relacion con la tabla mascota para representar la mascota que tiene el tratamiento
    // @JsonIgnore
    @ManyToOne
    @Nonnull
    private Mascota mascota;

    //Relacion con la tabla veterinario para representar el veterinario que tiene el tratamiento
    // @JsonIgnore
    @ManyToOne
    @Nonnull
    private Veterinario veterinario;

    //Relacion con la tabla droga para representar la droga que tiene el tratamiento
    // @JsonIgnore
    @ManyToOne
    @Nonnull
    private Droga droga;

    public Tratamiento(LocalDate fecha, Mascota mascota, Veterinario veterinario, Droga droga) {
        this.fecha = fecha;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.droga = droga;
    }
    
}