package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidad.Veterinario;

public interface VeterinarioService {

    public Veterinario SearchByCedula(String cedula);
    
    public Veterinario SearchById(Long id);

    public List<Veterinario> SearchAll();

    public void deleteById(Long id);

    public Veterinario update(Veterinario veterinario);

    public Veterinario add(Veterinario veterinario);

    public Veterinario updateState(Long id);

    //Buscar veterinarios que no sean el admin
    List<Veterinario> findByEspecialidadNotAdmin();

    //Dashboard - 3
    public Long countVeterinarios();

    //Dashboard - 4
    public Long countVeterinariosInactivos();
}
