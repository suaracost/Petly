package com.example.demo.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tratamiento {
    private String fecha;

    @ManyToOne
    private Mascota mascotaT;

    @ManyToOne
    private Veterinario veterinarioT;

    @ManyToOne
    private Droga drogaT;

    @Id
    @GeneratedValue
    private Long id;

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