package com.backup.backup.service;

import com.backup.backup.model.Bodega;
import com.backup.backup.repository.BodegaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegaService {

    private final BodegaRepository bodegaRepository;

    public BodegaService(BodegaRepository bodegaRepository) {
        this.bodegaRepository = bodegaRepository;
    }

    public List<Bodega> listarTodas() {
        return bodegaRepository.findAll();
    }

    public Optional<Bodega> buscarPorId(Long id) {
        return bodegaRepository.findById(id);
    }

    public Bodega guardar(Bodega bodega) {
        if (bodega.getEstado() == null) {
            bodega.setEstado(true);
        }
        return bodegaRepository.save(bodega);
    }

    public void eliminar(Long id) {
        bodegaRepository.deleteById(id);
    }
}