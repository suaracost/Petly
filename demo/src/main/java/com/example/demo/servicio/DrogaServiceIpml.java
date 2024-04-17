package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Droga;
import com.example.demo.repositorio.DrogaRepository;

@Service
public class DrogaServiceIpml implements DrogaService{

    @Autowired
    DrogaRepository repo;

    public Droga SearchById(Long id) {
        Optional<Droga> optionalDroga = repo.findById(id);
        return optionalDroga.orElse(null);
    };

    public Droga SearchByNombre(String nombre) {
        return repo.findByNombre(nombre);
    };

    public List<Droga> SearchAll() {
        return repo.findAll();
    };

    public void deleteById(Long id) {
        repo.deleteById(id);
    };

    public void update(Droga droga) {
        repo.save(droga);
    };

    public void add(Droga droga) {
        repo.save(droga);
    }

    @Override
    public Float totalVentasDrogas() {
        return repo.totalVentasDrogas();
    }

    @Override
    public Float totalGananciasDrogas() {
        return repo.totalGananciasDrogas();
    };
}
