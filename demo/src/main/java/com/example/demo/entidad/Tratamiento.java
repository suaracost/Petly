package com.example.demo.entidad;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tratamiento {

    private String fecha;
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

    public Tratamiento() {
    }

    public Tratamiento(String fecha, Mascota mascota, Veterinario veterinario, Droga droga) {
        this.fecha = fecha;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.droga = droga;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Mascota getmascota() {
        return mascota;
    }

    public void setmascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getveterinario() {
        return veterinario;
    }

    public void setveterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Droga getdroga() {
        return droga;
    }

    public void setdroga(Droga droga) {
        this.droga = droga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    
}