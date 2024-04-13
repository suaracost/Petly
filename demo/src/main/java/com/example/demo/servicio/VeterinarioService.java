package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidad.Veterinario;

public interface VeterinarioService {
    
    public Veterinario SearchById(Long id);

    public List<Veterinario> SearchAll();

    public void deleteById(Long id);

    public void update(Veterinario veterinario);

    public void add(Veterinario veterinario);
}
