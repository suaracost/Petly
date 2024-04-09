package com.example.demo.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    @ManyToOne
    private Mascota mascotaT;

    //Relacion con la tabla veterinario para representar el veterinario que tiene el tratamiento
    @JsonIgnore
    @ManyToOne
    private Veterinario veterinarioT;

    //Relacion con la tabla droga para representar la droga que tiene el tratamiento
    @JsonIgnore
    @ManyToOne
    private Droga drogaT;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Mascota getMascotaT() {
        return mascotaT;
    }

    public void setMascotaT(Mascota mascotaT) {
        this.mascotaT = mascotaT;
    }

    public Veterinario getVeterinarioT() {
        return veterinarioT;
    }

    public void setVeterinarioT(Veterinario veterinarioT) {
        this.veterinarioT = veterinarioT;
    }

    public Droga getDrogaT() {
        return drogaT;
    }

    public void setDrogaT(Droga drogaT) {
        this.drogaT = drogaT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    
}