package com.example.demo.DTO;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.entidad.Veterinario;

@Mapper
public interface VeterinarioMapper {
    VeterinarioMapper INSTANCE = Mappers.getMapper(VeterinarioMapper.class);

    //Convertir de veterinario a veterinarioDTO
    VeterinarioDTO convert(Veterinario veterinario);

    List<VeterinarioDTO> convertList(List<Veterinario> veterinarios);
    
}
